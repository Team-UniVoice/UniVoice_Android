package com.univoice.feature.util

import android.app.Activity
import android.widget.ImageButton

fun Activity.setupToolbarClickListener(button: ImageButton) {
    button.setOnClickListener {
        finish()
    }
}