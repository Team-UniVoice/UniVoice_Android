package com.univoice.feature.storage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.core_ui.theme.UniVoiceAndroidTheme
import com.univoice.databinding.FragmentStorageBinding

class StorageFragment : BindingFragment<FragmentStorageBinding>(R.layout.fragment_storage) {
    override fun initView() {
        binding.cvStorage.setContent {
            UniVoiceAndroidTheme {
                StorageScreen()
            }
        }
    }
}

@Composable
fun StorageScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Storage Fragment",
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StoragePreview() {
    UniVoiceAndroidTheme {
        StorageScreen()
    }
}