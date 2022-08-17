package com.example.lettervaultpro


import android.app.Application
import androidx.lifecycle.*
import androidx.paging.*
import com.example.lettervaultpro.data.Letter
import com.example.lettervaultpro.data.LetterDao
import com.example.lettervaultpro.repository.LetterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


enum class LetterStatus { UNLOCK, LOCK }
class LetterViewModel(private val letterDao: LetterDao, application: Application) : ViewModel() {

    private val letterRepository = LetterRepository(letterDao, application)

    init {
        getLatestLetter()
    }

    val letterList: Flow<PagingData<Letter>> =
        Pager(config = PagingConfig(
            pageSize = 25,
            enablePlaceholders = false,
            initialLoadSize = 25
        ),
            pagingSourceFactory = { letterRepository.refreshLetters() }
        )
            .flow
            .cachedIn(viewModelScope)


    val openedList=letterRepository.openedLetterList(letterList)

    val futureList=letterRepository.futureLetterList(letterList)



    var datalist: LiveData<Letter>? = null

    private fun insertItem(item: Letter) {
        viewModelScope.launch {
            val rowId = letterRepository.insertLetter(item)
            letterRepository.scheduleReminder(
                letterRepository.diff,
                TimeUnit.MILLISECONDS,
                rowId.toInt()
            )
        }
    }

    private fun getLatestLetter() {
        datalist = letterDao.getLetter().asLiveData()
    }

    fun addNewItem(
        hour: Int, minute: Int, subject: String,
        detail: String
    ) {
        val letter = letterRepository.addNewItem(hour, minute, subject, detail)
        insertItem(letter)

    }

}


class LetterViewModelFactory(
    private val letterDao: LetterDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LetterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LetterViewModel(letterDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


//    private var _Alllist = MutableLiveData<List<Letter>>()
//    var allList:LiveData<List<Letter>> =_Alllist


//    private val _status = MutableLiveData<LetterStatus>()
//    val status: LiveData<LetterStatus> = _status

//    private val _letter=MutableLiveData<Letter>()
//    val letter: LiveData<Letter> = _letter