package com.univoice.feature.storage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 16.dp),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 28.dp)
        ) {
            Text(
                text = "변상우",
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "님이 저장한 공지사항"
            )
        }
        LazyColumn {
            items(8) {
                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                )
                NoticeItem()
                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoragePreview() {
    UniVoiceAndroidTheme {
        StorageScreen()
    }
}