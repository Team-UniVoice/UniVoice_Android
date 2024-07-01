package com.univoice.feature.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import com.univoice.core_ui.theme.UniVoiceAndroidTheme
import com.univoice.core_ui.util.context.toast
import com.univoice.core_ui.view.UiState
import com.univoice.domain.entity.UserEntity
import com.univoice.feature.example.compose.ExampleComposeViewModel
import com.univoice.feature.example.compose.ExampleSideEffect
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ExampleComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniVoiceAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExampleComposeRoute()
                }
            }
        }
    }
}

@Composable
fun ExampleComposeRoute(viewModel: ExampleComposeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.getExampleState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.getExampleRecyclerview(2)
    }

    LaunchedEffect(viewModel.getExampleSideEffect, lifecycleOwner) {
        viewModel.getExampleSideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .onEach {
                when (it) {
                    is ExampleSideEffect.ExampleShowToast -> context.toast(it.message)
                }
            }.launchIn(lifecycleOwner.lifecycleScope)
    }

    when (state.exampleState) {
        is UiState.Loading -> Unit
        is UiState.Success -> ExampleComposeScreen((state.exampleState as UiState.Success).data)
        is UiState.Empty -> Unit
        is UiState.Failure -> Unit
    }
}

@Composable
fun ExampleComposeScreen(userData: List<UserEntity>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(userData) { _, user ->
            Image(
                painter = rememberImagePainter(data = user.avatar),
                contentDescription = "user",
                modifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UniVoiceAndroidTheme {
        ExampleComposeRoute()
    }
}