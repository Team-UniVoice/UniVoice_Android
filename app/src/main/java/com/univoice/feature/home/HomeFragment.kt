package com.univoice.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.core_ui.component.SetStatusBarColor
import com.univoice.core_ui.theme.Blue100
import com.univoice.core_ui.theme.Font_B01
import com.univoice.core_ui.theme.Font_B03
import com.univoice.core_ui.theme.Font_W01
import com.univoice.core_ui.theme.Gray100
import com.univoice.core_ui.theme.Gray200
import com.univoice.core_ui.theme.Gray500
import com.univoice.core_ui.theme.Mint200
import com.univoice.core_ui.theme.Regular
import com.univoice.core_ui.theme.UniVoiceAndroidTheme
import com.univoice.core_ui.theme.body1Semi
import com.univoice.core_ui.theme.body3Semi
import com.univoice.core_ui.theme.body4Regular
import com.univoice.core_ui.theme.body4Semi
import com.univoice.core_ui.theme.cap4Regular
import com.univoice.core_ui.theme.title3Regular
import com.univoice.core_ui.theme.title3Semi
import com.univoice.core_ui.theme.title4Bold
import com.univoice.databinding.FragmentHomeBinding

class HomeFragment :
    BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initView() {
        binding.cvHome.setContent {
            UniVoiceAndroidTheme {
                SetStatusBarColor(color = MaterialTheme.colorScheme.background)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeRoute()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(homeViewModel: HomeViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("유니보이스", style = title3Semi, color = Font_B01)
                },
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
        ) {
            if (false) {
                HomeEmptyScreen()
            } else {
                HomeScreen(homeViewModel)
            }
        }
    }
}

@Composable
fun HomeEmptyScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "아직 학생회가 등록되어 있지 않아,\n공지사항을 확인할 수 없어요.",
            style = title3Regular,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(bottom = 25.dp))
        Button(
            onClick = { },
            colors = ButtonColors(
                containerColor = Gray100,
                contentColor = Gray100,
                disabledContainerColor = Gray100,
                disabledContentColor = Gray100
            ),
            contentPadding = PaddingValues(horizontal = 45.dp, vertical = 13.dp)
        ) {
            Text(text = "학생회 등록 신청하기", style = title4Bold, color = Font_B01)
        }
    }
}

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    var choiceNoticeIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.padding(bottom = 11.dp))
        Text(text = "Quick Scan", style = title3Semi, modifier = Modifier.padding(start = 17.dp))
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(114.dp),
            horizontalArrangement = Arrangement.spacedBy(26.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
        ) {
            itemsIndexed(homeViewModel.mockQuickScanList) { _, data ->
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "Quick Scan",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(68.dp)
                                .aspectRatio(1f)
                                .clip(CircleShape)
                        )
                        Surface(
                            color = Mint200, shape = CircleShape, modifier = Modifier
                                .align(Alignment.TopEnd).offset(x = (-2).dp, y = (-2).dp)
                                .size(20.dp)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = data.count.toString(),
                                    style = body4Semi,
                                    color = Font_B01,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                    Spacer(Modifier.padding(bottom = 8.dp))
                    Text(
                        text = data.name,
                        style = body4Regular,
                        color = Font_B01,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(bottom = 28.dp))
        Text(text = "New 공지사항", style = title3Semi, modifier = Modifier.padding(start = 17.dp))
        Spacer(modifier = Modifier.padding(bottom = 4.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            itemsIndexed(homeViewModel.mockNoticeList) { index, data ->
                Button(
                    onClick = { choiceNoticeIndex = index },
                    colors = if (index != choiceNoticeIndex) {
                        ButtonColors(
                            containerColor = Gray200,
                            contentColor = Gray200,
                            disabledContainerColor = Gray200,
                            disabledContentColor = Gray200
                        )
                    } else {
                        ButtonColors(
                            containerColor = Gray500,
                            contentColor = Gray500,
                            disabledContainerColor = Gray500,
                            disabledContentColor = Gray500
                        )
                    },
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = data.name, style = body3Semi, color = Font_W01)
                }
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            itemsIndexed(homeViewModel.mockNoticeList[choiceNoticeIndex].noticeData) { _, data ->
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Regular,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .height(62.dp)
                    ) {
                        Surface(color = Blue100, shape = RoundedCornerShape(10.dp)) {
                            Text(
                                text = data.subTitle,
                                style = cap4Regular,
                                color = Font_B01,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.padding(bottom = 6.dp))
                        Text(text = data.title, style = body1Semi, color = Font_B01)
                        Spacer(modifier = Modifier.weight(1f))
                        Row {
                            Text(text = data.date, style = cap4Regular, color = Font_B03)
                            Text(
                                text = "|",
                                style = cap4Regular,
                                color = Font_B03,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            Text(text = data.like.toString(), style = cap4Regular, color = Font_B03)
                            Spacer(modifier = Modifier.padding(start = 6.dp))
                            Text(
                                text = data.views.toString(),
                                style = cap4Regular,
                                color = Font_B03,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    if (data.image) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "Notice Content",
                            modifier = Modifier
                                .size(58.dp)
                                .clip(RoundedCornerShape(5.dp)),
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    UniVoiceAndroidTheme {
        HomeRoute()
    }
}