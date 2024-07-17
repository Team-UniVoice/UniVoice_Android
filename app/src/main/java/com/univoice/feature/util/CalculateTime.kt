package com.univoice.feature.util

import android.content.Context
import com.univoice.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class CalculateTime(private val context: Context) {
    fun getCalculateTime(dateTimeString: String): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val targetDate = LocalDateTime.parse(dateTimeString, dateFormat)
        val currentDate = LocalDateTime.now()

        val minutesDifference = ChronoUnit.MINUTES.between(targetDate, currentDate)
        val hoursDifference = ChronoUnit.HOURS.between(targetDate, currentDate)
        val daysDifference = ChronoUnit.DAYS.between(targetDate, currentDate)

        return when {
            minutesDifference < TIME_THRESHOLD -> context.getString(R.string.feed_time_now)
            hoursDifference < TIME_THRESHOLD -> "$minutesDifference${context.getString(R.string.feed_time_minute)}"
            daysDifference < TIME_THRESHOLD -> "$hoursDifference${context.getString(R.string.feed_time_hour)}"
            else -> {
                val desiredFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd")
                return targetDate.format(desiredFormat)
            }
        }
    }

    companion object {
        private const val TIME_THRESHOLD = 1
    }


}