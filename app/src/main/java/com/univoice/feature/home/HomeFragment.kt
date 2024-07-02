package com.univoice.feature.home

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.core_ui.theme.UniVoiceAndroidTheme
import com.univoice.core_ui.theme.cap1Regular
import com.univoice.core_ui.theme.head1Bold
import com.univoice.databinding.FragmentHomeBinding
import com.univoice.feature.example.compose.ExampleComposeActivity
import com.univoice.feature.example.xml.ExampleActivity

class HomeFragment :
    BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initView() {
        binding.cvHome.setContent {
            UniVoiceAndroidTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        ExampleComposeActivity::class.java
                    )
                )
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Go to ExampleComposeActivity",
                style = head1Bold,
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {
                context.startActivity(
                    Intent(
                        context,
                        ExampleActivity::class.java
                    )
                )
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Go to ExampleActivity",
                style = cap1Regular
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    UniVoiceAndroidTheme {
        HomeScreen()
    }
}