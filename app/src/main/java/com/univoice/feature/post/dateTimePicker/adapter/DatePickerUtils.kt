package com.univoice.feature.post.dateTimePicker.adapter

import java.util.Calendar


const val AM = "오전"
const val PM = "오후"

class DatePickerUtils(private val startDate: Calendar, private val endDate: Calendar) {

    private val selectedDateUnvalidated: Calendar = Calendar.getInstance().also {
        it.time = startDate.time
    }

    var isPmSelectedUnvalidated = false

    init {
        isPmSelectedUnvalidated = startDate.get(Calendar.HOUR_OF_DAY) >= TWELVE
    }

    fun getAllDates(): ArrayList<Calendar> {
        val dates = ArrayList<Calendar>()
        val calendar = Calendar.getInstance().also { it.time = startDate.time }

        while (calendar.timeInMillis <= endDate.timeInMillis) {
            dates.add(calendar.clone() as Calendar)
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        return dates
    }

    fun getAllYears(): ArrayList<Calendar> {
        val years = ArrayList<Calendar>()
        val calendarStartYear = Calendar.getInstance().also { it.time = startDate.time }
        val calendarEndYear = Calendar.getInstance().also { it.time = endDate.time }

        calendarStartYear.add(Calendar.YEAR, -2)
        calendarEndYear.add(Calendar.YEAR, TWELVE)

        val totalYearsBetweenEnds =
            calendarEndYear.get(Calendar.YEAR) - calendarStartYear.get(Calendar.YEAR)

        for (count in 0..totalYearsBetweenEnds) {
            val date =
                Calendar.getInstance().also { it.timeInMillis = calendarStartYear.timeInMillis }
            date.add(Calendar.YEAR, count)
            years.add(date)
        }

        return years
    }

    fun getAllMonths(): ArrayList<Calendar> {
        val months = ArrayList<Calendar>()
        val calendarStartMonth = Calendar.getInstance().also { it.time = startDate.time }
        val calendarEndMonth = Calendar.getInstance().also { it.time = endDate.time }

        calendarStartMonth.add(Calendar.MONTH, -2)
        calendarEndMonth.add(Calendar.MONTH, 50)

        val totalMonthsBetweenEnds =
            (calendarEndMonth.get(Calendar.YEAR) - calendarStartMonth.get(Calendar.YEAR)) * 12 +
                    (calendarEndMonth.get(Calendar.MONTH) - calendarStartMonth.get(Calendar.MONTH))

        for (count in 0..totalMonthsBetweenEnds) {
            val date =
                Calendar.getInstance().also { it.timeInMillis = calendarStartMonth.timeInMillis }
            date.add(Calendar.MONTH, count)
            months.add(date)
        }

        return months
    }

    fun getHours(): ArrayList<Calendar> {
        val hoursList = ArrayList<Calendar>()
        val calendar = Calendar.getInstance()

        for (hour in -2..14) {
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            hoursList.add(calendar.clone() as Calendar)
        }
        return hoursList
    }

    fun getMinutes(): ArrayList<Calendar> {
        val minutesList = ArrayList<Calendar>()
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.MINUTE, 0)

        for (minute in -10..70 step 5) {
            calendar.set(Calendar.MINUTE, minute)
            minutesList.add(calendar.clone() as Calendar)
        }
        return minutesList
    }

    fun getMeridiem(): ArrayList<String> {
        val meridiem = ArrayList<String>()
        meridiem.add(AM)
        meridiem.add(PM)
        return meridiem
    }

    fun addEmptyValueInString(list: ArrayList<String>): ArrayList<String> {
        list.add(0, "")
        list.add(1, "")
        list.add("")
        list.add("")
        return list
    }

    fun setSelectedDate(dayOfYear: Int, year: Int) {
        selectedDateUnvalidated.set(Calendar.DAY_OF_YEAR, dayOfYear)
        selectedDateUnvalidated.set(Calendar.YEAR, year)
    }

    fun setSelectedDay(day: Int) {
        selectedDateUnvalidated.set(Calendar.DAY_OF_YEAR, day)
    }

    fun setSelectedMonth(month: Int) {
        selectedDateUnvalidated.set(Calendar.MONTH, month)
    }

    fun setSelectedYear(year: Int) {
        selectedDateUnvalidated.set(Calendar.YEAR, year)
    }

    fun setSelectedHour(hour: Int) {
        selectedDateUnvalidated.set(Calendar.HOUR_OF_DAY, hour)
    }

    companion object {
        const val TWELVE = 12
    }
}