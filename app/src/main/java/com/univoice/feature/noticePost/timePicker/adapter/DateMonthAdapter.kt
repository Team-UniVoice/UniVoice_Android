package com.univoice.feature.noticePost.timePicker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.univoice.R
import com.univoice.feature.noticePost.timePicker.dpToPx
import com.univoice.feature.noticePost.timePicker.getMonthFromCalendar
import java.util.Calendar

class DateMonthAdapter(
    val months: ArrayList<Calendar>,
    private val dividerHeight: Int
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = DateViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_date_number,
                parent,
                false
            )
        )
        view.binding.layoutDateNumber.layoutParams.height =
            dividerHeight.dpToPx(view.binding.tvDateNumber.context).toInt()
        return view
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as DateViewHolder

        viewHolder.binding.tvDateNumber.text = getMonthFromCalendar(months[position])
    }

    override fun getItemCount() = months.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}