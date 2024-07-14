package com.univoice.feature.storage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.core_ui.theme.Font_B01
import com.univoice.core_ui.theme.Regular
import com.univoice.core_ui.theme.UniVoiceAndroidTheme
import com.univoice.core_ui.theme.head5Bold
import com.univoice.databinding.FragmentStorageBinding

class StorageFragment : BindingFragment<FragmentStorageBinding>(R.layout.fragment_storage) {
    override fun initView() {
        binding.cvStorage.setContent {
            val navController = findNavController()
            UniVoiceAndroidTheme {
                StorageScreen(navController = navController)
            }
        }
    }
}

@Composable
fun StorageScreen(navController: NavController) {
    val viewModel: StorageViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 16.dp),
    ) {
        Text(
            modifier = Modifier.padding(top = 26.dp, bottom = 12.dp),
            text = stringResource(id = R.string.storage_toolbar_title),
            color = Font_B01,
            style = head5Bold
        )
        LazyColumn {
            items(viewModel.mockStorageList.size) { index ->
                val data = viewModel.mockStorageList[index]
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                R.id.action_fragment_storage_to_fragment_notice_detail
                            )
                        }
                ) {
                    StorageItem(data)
                    HorizontalDivider(
                        color = Regular,
                        thickness = 1.dp,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoragePreview() {
    UniVoiceAndroidTheme {
        StorageScreen(navController = NavController(LocalContext.current))
    }
}