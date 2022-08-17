package com.example.lettervaultpro

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.lettervaultpro.data.LetterRoomDatabase

class LetterApplication:Application() {
    val database:LetterRoomDatabase by lazy {LetterRoomDatabase.getDatabase(this)}

    override fun onCreate() {
        super.onCreate()
        val name = "letter_channel"
        val descriptionText = "letter_description"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        // Register the channel with the system
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val CHANNEL_ID = "letter_reminder_id"



    }

}