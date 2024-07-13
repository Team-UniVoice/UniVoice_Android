package com.univoice.feature.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.core_ui.component.SetStatusBarColor
import com.univoice.core_ui.theme.Blue300
import com.univoice.core_ui.theme.Font_B01
import com.univoice.core_ui.theme.Font_B02
import com.univoice.core_ui.theme.Font_B03
import com.univoice.core_ui.theme.Font_W01
import com.univoice.core_ui.theme.Gray50
import com.univoice.core_ui.theme.Gray800
import com.univoice.core_ui.theme.Mint400
import com.univoice.core_ui.theme.Regular
import com.univoice.core_ui.theme.UniVoiceAndroidTheme
import com.univoice.core_ui.theme.White
import com.univoice.core_ui.theme.body3Semi
import com.univoice.core_ui.theme.body4Regular
import com.univoice.core_ui.theme.button1Bold
import com.univoice.core_ui.theme.button2Semi
import com.univoice.core_ui.theme.cap3Regular
import com.univoice.core_ui.theme.cap4Regular
import com.univoice.core_ui.theme.head5Bold
import com.univoice.core_ui.theme.head7Semi
import com.univoice.core_ui.theme.title4Bold
import com.univoice.core_ui.theme.title4Semi
import com.univoice.databinding.FragmentHomeBinding
import com.univoice.domain.entity.NoticeListEntity
import com.univoice.domain.entity.QuickScanListEntity
import com.univoice.feature.quickscan.QuickScanActivity

class HomeFragment :
    BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initView() {
        binding.cvHome.setContent {
            val navController = findNavController()
            UniVoiceAndroidTheme {
                SetStatusBarColor(color = MaterialTheme.colorScheme.background)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeRoute(navController = navController)
                }
            }
        }
    }
}

@Composable
fun HomeRoute(homeViewModel: HomeViewModel = viewModel(), navController: NavController) {
    if (false) {
        HomeEmptyScreen()
    } else {
        HomeScreen(homeViewModel, navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeEmptyScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_appbar_logo),
                        contentDescription = stringResource(R.string.description_home_logo_img),
                    )
                },
                modifier = Modifier.height(46.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.text_home_empty),
                style = head7Semi,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(bottom = 16.dp))
            Button(
                onClick = { },
                colors = ButtonColors(
                    containerColor = Mint400,
                    contentColor = Mint400,
                    disabledContainerColor = Mint400,
                    disabledContentColor = Mint400
                ),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.btn_home_apply),
                    style = title4Bold,
                    color = Font_W01
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navController: NavController) {
    var choiceNoticeIndex by remember { mutableIntStateOf(0) }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                modifier = Modifier.height(0.dp),
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        Box {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .fillMaxSize(),
            ) {
                item {
                    HomeQuickScanContent(homeViewModel, context)
                }
                stickyHeader {
                    HomeNoticeContent(
                        homeViewModel = homeViewModel,
                        choiceNoticeIndex = choiceNoticeIndex,
                        onNoticeClick = { choiceNoticeIndex = it }
                    )
                }
                if (homeViewModel.mockNoticeList[choiceNoticeIndex].noticeData.isEmpty()) {
                    item {
                        HomeNoticeEmptyContent()
                    }
                } else {
                    itemsIndexed(homeViewModel.mockNoticeList[choiceNoticeIndex].noticeData) { index, data ->
                        HomeNoticeItem(
                            index,
                            data,
                            homeViewModel.mockNoticeList[choiceNoticeIndex].noticeData.lastIndex,
                            navController
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = { navController.navigate(R.id.action_fragment_home_to_fragment_notice_post) },
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(end = 16.dp, bottom = 20.dp)
                        .shadow(elevation = 7.dp, shape = CircleShape),
                    colors = ButtonColors(
                        containerColor = Mint400,
                        contentColor = Mint400,
                        disabledContainerColor = Mint400,
                        disabledContentColor = Mint400
                    ),
                    shape = CircleShape,
                    contentPadding = PaddingValues(all = 12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painterResource(id = R.drawable.ic_home_floating_action_btn),
                            contentDescription = stringResource(R.string.btn_home_notice_post),
                            tint = White,
                            modifier = Modifier.padding(end = 2.dp)
                        )
                        Text(
                            text = stringResource(R.string.btn_home_floating_action_btn),
                            color = White,
                            style = button1Bold
                        )
                    }
                }
            }
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun HomeNoticeItem(
    index: Int,
    data: NoticeListEntity,
    lastIndex: Int,
    navController: NavController
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = if (index == 0) {
                Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp, top = 8.dp, bottom = 12.dp)
                    .clickable {
                        navController.navigate(
                            R.id.action_fragment_home_to_fragment_notice_detail
                        )
                    }
            } else {
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 12.dp)
                    .clickable {
                        navController.navigate(
                            R.id.action_fragment_home_to_fragment_notice_detail
                        )
                    }
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier.height(62.dp)
                ) {
                    Surface(
                        border = BorderStroke(width = 1.dp, color = Regular),
                        shape = RoundedCornerShape(30.dp),
                    ) {
                        Text(
                            text = data.subTitle,
                            style = cap4Regular,
                            color = Font_B02,
                            modifier = Modifier.padding(
                                horizontal = 6.dp,
                                vertical = 2.dp
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(bottom = 6.dp))
                    Text(
                        text = data.title,
                        style = title4Semi,
                        color = Font_B01,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Row {
                        Text(
                            text = data.startDate,
                            style = cap3Regular,
                            color = Font_B03
                        )
                        Text(
                            text = stringResource(R.string.text_home_date_tilde),
                            style = cap3Regular,
                            color = Font_B03,
                            modifier = Modifier.padding(horizontal = 2.dp)
                        )
                        Text(text = data.endDate, style = cap3Regular, color = Font_B03)
                        Text(
                            text = stringResource(R.string.text_home_divider),
                            style = cap3Regular,
                            color = Font_B03,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Icon(
                            painterResource(id = R.drawable.ic_home_like),
                            contentDescription = stringResource(R.string.description_home_notice_like_icon),
                            modifier = Modifier.padding(end = 2.dp)
                        )
                        Text(
                            text = data.like.toString(),
                            style = cap3Regular,
                            color = Font_B03
                        )
                        Icon(
                            painterResource(id = R.drawable.ic_home_like),
                            contentDescription = stringResource(R.string.description_home_notice_views_icon),
                            modifier = Modifier.padding(start = 6.dp, end = 2.dp)
                        )
                        Text(
                            text = data.views.toString(),
                            style = cap3Regular,
                            color = Font_B03,
                        )
                    }
                }
            }
            Box {
                if (data.image) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = stringResource(R.string.text_home_notice_title),
                        modifier = Modifier
                            .size(58.dp)
                            .clip(RoundedCornerShape(5.dp)),
                    )
                }
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = Regular,
        )
        if (index == lastIndex) {
            Spacer(modifier = Modifier.padding(bottom = 16.dp))
        }
    }
}

@Composable
fun HomeNoticeEmptyContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.text_home_notice_empty),
            style = head7Semi,
            color = Font_B01,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 170.dp, bottom = 193.dp)
        )
    }
}

@Composable
fun HomeNoticeContent(
    homeViewModel: HomeViewModel,
    choiceNoticeIndex: Int,
    onNoticeClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        Text(
            text = stringResource(R.string.text_home_notice_title),
            style = head5Bold,
            color = Font_B01,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.padding(bottom = 14.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            itemsIndexed(homeViewModel.mockNoticeList) { index, data ->
                Button(
                    onClick = { onNoticeClick(index) },
                    colors = if (index != choiceNoticeIndex) {
                        ButtonColors(
                            containerColor = Gray50,
                            contentColor = Gray50,
                            disabledContainerColor = Gray50,
                            disabledContentColor = Gray50
                        )
                    } else {
                        ButtonColors(
                            containerColor = Mint400,
                            contentColor = Mint400,
                            disabledContainerColor = Mint400,
                            disabledContentColor = Mint400
                        )
                    },
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .defaultMinSize(
                            minWidth = 47.dp,
                            minHeight = 32.dp
                        ),
                ) {
                    Text(
                        text = data.name, style = body3Semi,
                        color = if (index != choiceNoticeIndex) {
                            Gray800
                        } else {
                            Font_W01
                        },
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
    }
}

@Composable
fun HomeQuickScanContent(homeViewModel: HomeViewModel, context: Context) {
    Box(
        modifier = Modifier.height(46.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_appbar_logo),
            contentDescription = stringResource(R.string.description_home_logo_img),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    Text(
        text = stringResource(R.string.text_home_quick_scan_title),
        style = head5Bold,
        color = Font_B01,
        modifier = Modifier.padding(start = 16.dp)
    )
    Spacer(modifier = Modifier.padding(bottom = 11.dp))
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
    ) {
        items(
            items = homeViewModel.mockQuickScanList,
            key = { data -> data.name },
        ) { data ->
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(95.dp)
                    .clickable {
                        context.startActivity(Intent(context, QuickScanActivity::class.java))
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HomeQuickScanItem(data)
            }
        }
    }
    Spacer(modifier = Modifier.padding(bottom = 8.dp))
}

@Composable
fun HomeQuickScanItem(data: QuickScanListEntity) {
    Box {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_background),
            contentDescription = stringResource(R.string.text_home_quick_scan_title),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(68.dp)
                .aspectRatio(1f)
                .clip(CircleShape)
        )
        Surface(
            color = Blue300,
            shape = CircleShape,
            modifier = if (data.count.toString().length == 1) {
                Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-4.5).dp, y = (-6).dp)
            } else {
                Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 2.5.dp, y = (-6).dp)
            }
        ) {
            Text(
                text = data.count.toString(),
                style = button2Semi,
                color = Font_W01,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    vertical = 2.dp,
                    horizontal = 6.dp
                ),
            )
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

//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
//    UniVoiceAndroidTheme {
//        HomeRoute()
//    }
//}