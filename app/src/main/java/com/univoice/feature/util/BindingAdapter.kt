package com.univoice.feature.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.univoice.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    view.load(url) {
        placeholder(R.drawable.img_quick_scan_placeholder)
        error(R.drawable.img_quick_scan_placeholder)
    }
}

@BindingAdapter("setCircleImage")
fun ImageView.setCircleImage(img: String?) {
    load(img) {
        transformations(RoundedCornersTransformation(1000f))
    }
}
