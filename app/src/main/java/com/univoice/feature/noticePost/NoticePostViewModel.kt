package com.univoice.feature.noticePost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NoticePostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    private val _postNoticeState = MutableSharedFlow<UiState<Any>>()
    val postNoticeState: SharedFlow<UiState<Any>> get() = _postNoticeState.asSharedFlow()

    fun postNotice(
        title: String,
        content: String,
        target: String?,
        startTime: String?,
        endTime: String?,
        noticeImages: List<File>?
    ) = viewModelScope.launch {
        _postNoticeState.emit(UiState.Loading)
        postRepository.postSignUp(
            title,
            content,
            target,
            startTime,
            endTime,
            noticeImages,
        ).onSuccess { _postNoticeState.emit(UiState.Success(it)) }
            .onFailure { _postNoticeState.emit(UiState.Failure(it.message.toString())) }
    }
}