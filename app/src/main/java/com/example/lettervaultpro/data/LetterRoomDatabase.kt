package com.example.lettervaultpro.data

import android.content.Context
import androidx.room.*

@Database(entities = [Letter::class], version = 2, exportSchema = false)
@TypeConverters(DataClassConverter::class)
abstract class LetterRoomDatabase: RoomDatabase() {

    abstract fun letterDao():LetterDao

    companion object{
        @Volatile
        private var INSTANCE: LetterRoomDatabase? = null

        fun getDatabase(context: Context): LetterRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LetterRoomDatabase::class.java,
                    "msg_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}