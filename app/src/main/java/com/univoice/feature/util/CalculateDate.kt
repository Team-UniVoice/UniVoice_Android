package com.univoice.feature.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
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

    fun getCalculateNoticeDay(startTime: String, endTime: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val startDateTime = LocalDateTime.parse(startTime, inputFormatter)
        val endDateTime = LocalDateTime.parse(endTime, inputFormatter)

        val outputFormatter = DateTimeFormatter.ofPattern("MM/dd(E)", Locale.KOREAN)
        return startDateTime.format(outputFormatter) + " ~ " + endDateTime.format(outputFormatter)
    }
    fun convertToFullDateFormat(dateStr: String): String {
        val inputFormat = SimpleDateFormat("M월 d일(E)", Locale.KOREA)
        val outputFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA)
        val date = inputFormat.parse(dateStr)

        val calendar = Calendar.getInstance().apply {
            time = date
            set(Calendar.YEAR, 2024)
        }

        return outputFormat.format(calendar.time)
    }

    fun convertToFullTimeFormat(period: String, hourStr: String, minuteWithColon: String): String {
        val minuteStr = minuteWithColon.replace(":", "")

        val amPm = if (period == "AM") "오전" else "오후"

        return String.format("%s %s시 %s분", amPm, hourStr, minuteStr)
    }

    fun getHasTime(dateTime: String): Boolean {
        return dateTime.substringAfter('T') != "00:00:00"
    }
}