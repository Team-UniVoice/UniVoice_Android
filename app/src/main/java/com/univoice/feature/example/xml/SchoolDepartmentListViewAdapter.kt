package com.univoice.feature.example.xml

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.univoice.R

class SchoolDepartmentListAdapter(private val context: Context, private var highlightText: String) :
    ListAdapter<String, SchoolDepartmentViewHolder>(SchoolDepartmentDiffCallback()) {

    private var itemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolDepartmentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false)
        return SchoolDepartmentViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: SchoolDepartmentViewHolder, position: Int) {
        val schoolName = getItem(position)
        val spannable = SpannableStringBuilder(schoolName)

        if (highlightText.isNotEmpty() && schoolName.contains(highlightText, ignoreCase = true)) {
            var startIndex = schoolName.indexOf(highlightText, ignoreCase = true)
            while (startIndex >= 0) {
                val endIndex = startIndex + highlightText.length
                spannable.setSpan(
                    StyleSpan(Typeface.BOLD),
                    startIndex,
                    endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                startIndex = schoolName.indexOf(highlightText, startIndex + highlightText.length, ignoreCase = true)
            }
        }

        holder.textView.text = spannable
    }

    fun setHighlightText(newText: String) {
        highlightText = newText
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }

    class SchoolDepartmentDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
