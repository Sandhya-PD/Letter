package com.example.lettervaultpro.repository


import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import androidx.work.*
import com.example.lettervaultpro.LetterStatus
import com.example.lettervaultpro.data.Letter
import com.example.lettervaultpro.data.LetterDao
import com.example.lettervaultpro.worker.LetterReminderWorker
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates



class LetterRepository(private val letterDao: LetterDao, application: Application) {

    private val workManager = WorkManager.getInstance(application)
    var diff by Delegates.notNull<Long>()

    var selectedDay by Delegates.notNull<Int>()
    lateinit var openingDate: String

    private val _status = MutableLiveData<LetterStatus>()
    val status: LiveData<LetterStatus> = _status


    private var _letterSubject = MutableLiveData<String>()
    var letterSubject: LiveData<String> = _letterSubject

    //
    private val _letterDetails = MutableLiveData<String>()
    val letterDetails: LiveData<String> = _letterDetails

//    private var _openTime = MutableLiveData<String>()
//    val openTime: LiveData<String> = _openTime

    private var _singleLetter = MutableLiveData<Letter>()
    val singleLetter: LiveData<Letter> = _singleLetter


    fun openedLetterList(letterList: Flow<PagingData<Letter>>):
         Flow<PagingData<Letter>>{
        return letterList.map {
            it.filter { letter->letter.status }
        }
    }


    fun futureLetterList(letterList: Flow<PagingData<Letter>>):
          Flow<PagingData<Letter>>{
        return letterList.map {
            it.filter { letter->!letter.status }
        }
    }



    fun scheduleReminder(
        duration: Long,
        unit: TimeUnit,
        LetterId: Int

    ) {
        val data = workDataOf(
            "ID" to LetterId
        )

        val workRequest = OneTimeWorkRequestBuilder<LetterReminderWorker>()
            .setInputData(data)
            .setInitialDelay(duration, unit)
            .build()


        workManager.enqueueUniqueWork(
            LetterId.toString(), ExistingWorkPolicy.APPEND_OR_REPLACE,
            workRequest
        )

    }

    private fun cancelWork(name: Int) {
        workManager.cancelUniqueWork(name.toString())
    }

    fun settingLockImage(imgStatus: Boolean) {

        if (imgStatus) {
            _status.value = LetterStatus.UNLOCK

        } else {
            _status.value = LetterStatus.LOCK
        }
    }

    private fun getNewItemEntry(
        subject: String,
        detail: String,
        created_date: String,
        selected_date: String,
        status: Boolean
    ): Letter {
        return Letter(
            id = 0,
            subject = subject,
            detail = detail,
            created_date = created_date,
            selected_date = selected_date,
            status = status
        )
    }

    @SuppressLint("DefaultLocale")
    fun addNewItem(
        hour: Int, minute: Int,
        subject: String, detail: String,
    ): Letter {


        val hourOfDay = hour % 12
        val timeDate = (java.lang.String.format(
            "%d:%02d%s", if (hourOfDay == 0) 12 else hourOfDay,
            minute, if (hour < 12) "AM" else "PM"
        )
                )


        val dateFormatter = SimpleDateFormat("MMM dd yyyy")
        var currentDay = dateFormatter.format(Date())


        val currentTime = LocalDateTime.now()
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DATE)

        val currentHour=cal.get(Calendar.HOUR_OF_DAY)
        val currentMinute=cal.get(Calendar.MINUTE)

//
//       if(hour<currentHour&&minute<currentHour || hour>1){
//           cal.add(Calendar.DAY_OF_YEAR, 1)
//           val tomorrow: Date = cal.time
//           currentDay = dateFormatter.format(tomorrow)
//           selectedDay = cal.get(Calendar.DATE)
//           Log.d("date", "$day")
//        hour.milliseconds < currentHour.milliseconds &&minute.milliseconds <currentHour.milliseconds
//       }

        if(hour<currentHour || minute<currentMinute){
            cal.add(Calendar.DAY_OF_YEAR, 1)
            val tomorrow: Date = cal.time
            currentDay = dateFormatter.format(tomorrow)
            selectedDay = cal.get(Calendar.DATE)
            Log.d("date", "$day")
        }


//        if (hour in 1..6) {
//            cal.add(Calendar.DAY_OF_YEAR, 1)
//            val tomorrow: Date = cal.time
//            currentDay = dateFormatter.format(tomorrow)
//            selectedDay = cal.get(Calendar.DATE)
//            Log.d("date", "$day")
//        }

        openingDate = "$currentDay,$timeDate"
        Log.d("DateTime", openingDate)

        val futTime = Calendar.getInstance()
        futTime.set(2022, month, day, hour, minute)

        Log.d("msg", "$year $month $day $hour $minute")

        val today = Calendar.getInstance()

        val offset = today.get(Calendar.ZONE_OFFSET) + today.get(Calendar.DST_OFFSET)

        val futureTime = (futTime.timeInMillis + offset) % (24 * 60 * 60 * 1000)
        val todayTime = (today.timeInMillis + offset) % (24 * 60 * 60 * 1000)

        diff = if (futureTime < todayTime) {
            todayTime - futureTime
        } else {
            futureTime - todayTime
        }

        Log.d("HEREEE DIFF >", " $diff , $futureTime - $todayTime :: ")

        val newItem = getNewItemEntry(subject, detail, currentTime.toString(), openingDate, false)
        return newItem


    }


    suspend fun insertLetter(letter: Letter): Long {
        return letterDao.insert(letter)

    }


    fun refreshLetters(): PagingSource<Int, Letter> {

        return letterDao.getAllLetters()

    }

    suspend fun deleteLetter(id: Int) {
        withContext(Dispatchers.IO) {
            letterDao.delete(id)
        }
        cancelWork(id)
    }

    suspend fun updateLetter(letter: Letter) {
        withContext(Dispatchers.IO)
        {
            letterDao.update(letter)
        }
    }

    suspend fun getLetterById(id: Int): Letter {
        val msg = letterDao.getLetterByID(id)

        val dateTimeCurrent =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd yyyy,h:mma"))

        if (msg.selected_date <= dateTimeCurrent && !msg.status) {
            updateLetter(msg.copy(status = true, selected_date = dateTimeCurrent))

        }

        when {
            msg.status -> {
                _singleLetter.value=msg
                _letterSubject.value = msg.subject
                _letterDetails.value = msg.detail
//                _openTime.value = msg.selected_date
                settingLockImage(true)

            }
            msg.selected_date <= dateTimeCurrent -> {
                _singleLetter.value=msg
                _letterSubject.value = msg.subject
                _letterDetails.value = msg.detail
//                _openTime.value = dateTimeCurrent
                settingLockImage(true)
            }
            else -> {
                _singleLetter.value=msg
                _letterSubject.value = ""
                _letterDetails.value = ""
//                _openTime.value = msg.selected_date
                settingLockImage(false)

            }
        }
        return msg
    }

}














