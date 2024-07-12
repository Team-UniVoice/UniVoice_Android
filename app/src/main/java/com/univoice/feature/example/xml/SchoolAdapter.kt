package com.univoice.feature.example.xml

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.TextAppearanceSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.univoice.R

class SchoolAdapter(context: Context, private val resource: Int, private val items: List<String>, private var highlightText: String)
    : ArrayAdapter<String>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

        val schoolName = items[position]
        val textView = view.findViewById<TextView>(R.id.tv_listview_item)

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

        textView.text = spannable
        return view
    }

    fun setHighlightText(newText: String) {
        highlightText = newText
        notifyDataSetChanged()
    }
}
