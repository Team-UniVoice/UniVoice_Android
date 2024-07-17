package com.univoice.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.data.dto.request.RequestDepartmentDto
import com.univoice.domain.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DepartmentInputViewModel @Inject constructor(
    private val signupRepository: SignUpRepository
) : ViewModel() {

    private val _departmentList = MutableStateFlow<List<String>>(emptyList())
    val departmentList: StateFlow<List<String>> = _departmentList

    fun fetchDepartments(universityName: String) {
        viewModelScope.launch {
            val request = RequestDepartmentDto(universityName)
            signupRepository.postDepartments(request).onSuccess { response ->
                _departmentList.value = response.data ?: emptyList()
            }.onFailure {
                Timber.e(it, "")
            }
        }
    }
}
