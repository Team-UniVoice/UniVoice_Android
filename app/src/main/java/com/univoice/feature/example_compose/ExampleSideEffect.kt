package com.univoice.feature.example_compose

sealed class ExampleSideEffect {
    data class ShowToast(val message: String) : ExampleSideEffect()
}