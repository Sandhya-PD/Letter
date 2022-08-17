package com.example.lettervaultpro.utilies

import com.example.lettervaultpro.data.Letter
import java.util.*

val testLetters = arrayListOf(
    Letter(1,"Hello", "Apple", "Aug 03 2022,10:30AM", "Aug 03 2022,10:50AM",true),
    Letter(1,"HH", "Description", "Aug 03 2022,11:30AM", "Aug 03 2022,11:55AM",false),

)
val testPlant = testLetters[0]

val testCalendar: Calendar = Calendar.getInstance().apply {
    set(Calendar.YEAR, 1998)
    set(Calendar.MONTH, Calendar.SEPTEMBER)
    set(Calendar.DAY_OF_MONTH, 4)
}