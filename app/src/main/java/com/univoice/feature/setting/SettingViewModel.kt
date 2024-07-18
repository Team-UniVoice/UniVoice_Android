package com.univoice.feature.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.core_ui.view.UiState
import com.univoice.domain.entity.SettingUserEntity
import com.univoice.domain.repository.SettingRepository
import com.univoice.domain.repository.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository,
    private val userInfoRepository: UserInfoRepository
) : ViewModel() {
    private val _getMyPageState = MutableStateFlow<UiState<SettingUserEntity>>(UiState.Empty)
    val getMyPageState: StateFlow<UiState<SettingUserEntity>> = _getMyPageState

    init {
        getMyPage()
    }

    private fun getMyPage() = viewModelScope.launch {
        _getMyPageState.emit(UiState.Loading)
        settingRepository.getMyPage().fold(
            {
                _getMyPageState.emit(UiState.Success(it))
            },
            { _getMyPageState.emit(UiState.Failure(it.message.toString())) }
        )
    }

    fun saveCheckLogin(checkLogin: Boolean) =
        viewModelScope.launch { userInfoRepository.saveCheckLogin(checkLogin) }
}