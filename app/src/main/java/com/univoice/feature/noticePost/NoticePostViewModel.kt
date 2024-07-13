package com.univoice.feature.noticePost

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NoticePostViewModel : ViewModel() {
    private val _photoUri = MutableStateFlow<String?>(null)
    val photoUri: StateFlow<String?> = _photoUri

    fun setPhotoUri(uri: String?) {
        _photoUri.value = uri
    }
}