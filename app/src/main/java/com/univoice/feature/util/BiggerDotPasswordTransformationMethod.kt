package com.univoice.feature.util

import android.text.method.PasswordTransformationMethod
import android.view.View

class BiggerDotPasswordTransformationMethod : PasswordTransformationMethod() {
    override fun getTransformation(source: CharSequence?, view: View?): CharSequence {
        return BiggerDotCharSequence(super.getTransformation(source, view))
    }

    private class BiggerDotCharSequence(private val original: CharSequence) : CharSequence {
        override val length: Int
            get() = original.length

        override fun get(index: Int): Char {
            return '●'
        }

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
            return BiggerDotCharSequence(original.subSequence(startIndex, endIndex))
        }
    }
}