package com.univoice.feature.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CalculateDate {
    fun getCalculateDate(dateTimeString: String): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        val targetDate = LocalDate.parse(dateTimeString.substringBefore('T'))
        return targetDate.format(dateFormat)
    }
}