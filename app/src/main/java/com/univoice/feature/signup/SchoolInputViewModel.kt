package com.univoice.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.univoice.domain.repository.UniversityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolInputViewModel @Inject constructor(
    private val universityRepository: UniversityRepository
) : ViewModel() {

    private val _schoolList = MutableStateFlow<List<String>>(emptyList())
    val schoolList: StateFlow<List<String>> = _schoolList

    init {
        fetchUniversityNames()
    }

    private fun fetchUniversityNames() {
        viewModelScope.launch {
            universityRepository.getUniversityNames().onSuccess { response ->
                _schoolList.value = response
            }.onFailure {
            }
        }
    }
}
