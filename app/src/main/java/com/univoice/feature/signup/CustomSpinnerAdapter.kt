package com.univoice.feature.signup

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.univoice.R

class CustomSpinnerAdapter(
    context: Context,
    resource: Int,
    private val items: Array<String>
) : ArrayAdapter<String>(context, resource, items) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val textView = view as TextView
        textView.setTextAppearance(R.style.TextAppearance_UniVoice_caption1Regular)
        if (position == 0) {
            view.visibility = View.GONE
            view.layoutParams = ViewGroup.LayoutParams(0, 1)
        } else {
            view.visibility = View.VISIBLE
            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        return view
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val textView = view as TextView
        textView.setPadding(21, textView.paddingTop, textView.paddingRight, textView.paddingBottom)
        if (position == 0) {
            textView.setTextAppearance(R.style.TextAppearance_UniVoice_head7Regular)
            textView.setTextColor(ContextCompat.getColor(context, R.color.font_B04))
        } else {
            textView.setTextAppearance(R.style.TextAppearance_UniVoice_title4Semi)
        }
        return view
    }
}
