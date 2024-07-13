package com.univoice.feature.util

import android.content.Context
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.univoice.R

class BiggerDotPasswordTransformationMethod(private val context: Context) :
    PasswordTransformationMethod() {
    override fun getTransformation(source: CharSequence?, view: View?): CharSequence {
        return BiggerDotCharSequence(super.getTransformation(source, view), context)
    }

    private class BiggerDotCharSequence(
        private val original: CharSequence,
        private val context: Context
    ) : CharSequence {
        override val length: Int
            get() = original.length

        override fun get(index: Int): Char {
            return context.getString(R.string.login_pwd_mask).single()
        }

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
            return BiggerDotCharSequence(original.subSequence(startIndex, endIndex), context)
        }
    }
}