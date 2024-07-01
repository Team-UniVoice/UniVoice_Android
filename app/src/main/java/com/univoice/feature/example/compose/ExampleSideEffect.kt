package com.univoice.feature.example.compose

sealed class ExampleSideEffect {
    data class ExampleShowToast(val message: String) : ExampleSideEffect()
}