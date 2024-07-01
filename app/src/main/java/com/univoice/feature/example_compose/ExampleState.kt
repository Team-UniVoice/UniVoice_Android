package com.univoice.feature.example_compose

import com.univoice.core_ui.view.UiState
import com.univoice.domain.entity.UserEntity

data class ExampleState(
    val exampleState: UiState<List<UserEntity>>
)