package com.example.lettervaultpro.data

import androidx.room.TypeConverter
import java.sql.Date

class DataClassConverter {

        @TypeConverter
        fun fromTimestamp(value: Long?): Date? {
            return value?.let { Date(it) }
        }

        @TypeConverter
        fun dateToTimestamp(date: Date?): Long? {
            return if (date == null) null else date.getTime()
        }
    }
