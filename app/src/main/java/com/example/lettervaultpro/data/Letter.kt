package com.example.lettervaultpro.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize
import java.sql.Date
import java.sql.Timestamp

//@Parcelize
@Entity(tableName = "letter")
data class Letter(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Int,
    @ColumnInfo(name="subject")
    val subject: String,
    @ColumnInfo(name="detail")
    val detail: String,
    @ColumnInfo(name="created_date")
    val created_date: String,
    @ColumnInfo(name = "open_date")
    var selected_date: String,
    @ColumnInfo(name="status")
    var status:Boolean
    )


