package com.univoice.feature.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class CalculateDate {
    fun getCalculateDate(dateTimeString: String): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        val targetDate = LocalDate.parse(dateTimeString.substringBefore('T'))
        return targetDate.format(dateFormat)
    }

    fun getCalculateNoticeDate(startTime: String, endTime: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val startDateTime = LocalDateTime.parse(startTime, inputFormatter)
        val endDateTime = LocalDateTime.parse(endTime, inputFormatter)

        val outputFormatter = DateTimeFormatter.ofPattern("MM/dd(E) HH:mm", Locale.KOREAN)
        return startDateTime.format(outputFormatter) + " ~ " + endDateTime.format(outputFormatter)
    }
}