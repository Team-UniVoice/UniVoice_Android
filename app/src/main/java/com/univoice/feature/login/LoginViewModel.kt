package com.univoice.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.domain.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val _userId = MutableStateFlow<String?>(null)
    val userId: StateFlow<String?> = _userId

    private val _userPwd = MutableStateFlow<String?>(null)
    val userPwd: StateFlow<String?> = _userPwd

    init {
        viewModelScope.launch {
            userPreferencesRepository.getUserId().collect {
                _userId.value = it
            }
            userPreferencesRepository.getUserPwd().collect {
                _userPwd.value = it
            }
        }
    }

    fun saveUserCredentials(userId: String, userPwd: String) {
        viewModelScope.launch {
            userPreferencesRepository.saveUserId(userId, userPwd)
        }
    }
}