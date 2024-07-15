package com.univoice.feature.signup

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.univoice.R

class SchoolDepartmentListViewHolder(itemView: View, private val itemClickListener: ((Int) -> Unit)?) :
    RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.tv_listview_item)

    init {
        itemView.setOnClickListener {
            itemClickListener?.invoke(adapterPosition)
        }
    }
}
