package com.example.lettervaultpro.worker

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.lettervaultpro.LetterApplication
import com.example.lettervaultpro.MainActivity
import com.example.lettervaultpro.OpenMessage
import com.example.lettervaultpro.R

class LetterReminderWorker(context: Context,workerParams: WorkerParameters):
Worker(context,workerParams)
{
    // Arbitrary id number
    val notificationId = 1

    override fun doWork(): Result {
//        val LetterName = inputData.getString(nameKey)
        val LetterId = inputData.getInt("ID",0)

        val preferences =applicationContext.getSharedPreferences("com.example.lettervaultpro_preferences",0)
        val prefValue=preferences!!.getBoolean("notif_state",false)


        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        intent.action = "open_fragment"
//        intent.putExtra("Testing", "Test Work")
        if (LetterId != null) {

            Log.d("open", LetterId.toString())
        }

        intent.putExtra("ModelID",LetterId)
        val requestCode=System.currentTimeMillis().toInt()


        val pendingIntent: PendingIntent = PendingIntent
            .getActivity(applicationContext, requestCode, intent, 0)



        if(prefValue) {

            val builder = applicationContext.let {
                NotificationCompat.Builder(it, LetterApplication.CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_baseline_done)
                    .setContentTitle("Letter Vault!")
                    .setContentText("Letter is ready to open")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    //open the activity
                    .setContentIntent(pendingIntent)
                    // which automatically removes the notification when the user taps it
                    .setAutoCancel(true)
            }

            //To make the notification appear
            with(NotificationManagerCompat.from(applicationContext)) {
                notify(notificationId, builder.build())
            }
        }

        return Result.success()
    }



    companion object {
        const val nameKey="NAME"
        const val Id=0

    }

}