package com.univoice.feature.post.dateTimePicker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.univoice.R

class TimeMeridiemAdapter(
    val meridiem: ArrayList<String>,
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

        if (meridiem[position].isNotEmpty())
            viewHolder.binding.tvDateNumber.text = meridiem[position]
        else
            viewHolder.binding.tvDateNumber.text = ""
    }

    override fun getItemCount() = meridiem.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}