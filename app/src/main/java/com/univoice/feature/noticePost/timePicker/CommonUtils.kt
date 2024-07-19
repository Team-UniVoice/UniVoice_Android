package com.univoice.feature.noticePost.timePicker

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@SuppressLint("SimpleDateFormat")
fun getDateFromCalendar(calendar: Calendar): String {
    val sdf = SimpleDateFormat("M월 dd일 EEE", Locale.KOREA)
    return sdf.format(calendar.time)
}

@SuppressLint("SimpleDateFormat")
fun getYearFromCalendar(calendar: Calendar): String {
    val sdf = SimpleDateFormat("YYYY년", Locale.KOREA)
    return sdf.format(calendar.time)
}

@SuppressLint("SimpleDateFormat")
fun getMonthFromCalendar(calendar: Calendar): String {
    val sdf = SimpleDateFormat("M월", Locale.KOREA)
    return sdf.format(calendar.time)
}

@SuppressLint("SimpleDateFormat")
fun getDayFromCalendar(calendar: Calendar): String {
    val sdf = SimpleDateFormat("d일", Locale.KOREA)
    return sdf.format(calendar.time)
}

@SuppressLint("SimpleDateFormat")
fun getHourFromCalendar(calendar: Calendar): String {
    val sdf = SimpleDateFormat("hh", Locale.KOREA)
    return sdf.format(calendar.time)
}

@SuppressLint("SimpleDateFormat")
fun getMinuteFromCalendar(calendar: Calendar): String {
    val sdf = SimpleDateFormat("mm", Locale.KOREA)
    return sdf.format(calendar.time)
}

fun RecyclerView.initVerticalAdapter(
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
    hasFixedSize: Boolean
) {
    val llm = LinearLayoutManager(this.context)
    this.setHasFixedSize(hasFixedSize)
    this.setItemViewCacheSize(10)
    this.layoutManager = llm
    adapter.setHasStableIds(true)
    this.adapter = adapter
}

fun Int.dpToPx(context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        context.resources.displayMetrics
    )
}