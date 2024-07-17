package com.univoice.feature.storage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.univoice.R
import com.univoice.core_ui.theme.Font_B01
import com.univoice.core_ui.theme.Font_B02
import com.univoice.core_ui.theme.Font_B03
import com.univoice.core_ui.theme.Regular
import com.univoice.core_ui.theme.cap3Regular
import com.univoice.core_ui.theme.cap4Regular
import com.univoice.core_ui.theme.title4Semi
import com.univoice.domain.entity.NoticeListEntity

@Composable
fun StorageItem(data: NoticeListEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 12.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = data.category,
                color = Font_B02,
                style = cap4Regular,
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(30.dp))
                    .border(width = 1.dp, color = Regular, shape = RoundedCornerShape(30.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp),
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = data.title,
                color = Font_B01,
                style = title4Semi
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data.date.toString(),
                    color = Font_B03,
                    style = cap3Regular
                )
                Text(
                    color = Font_B03,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(12.dp),
                    text = stringResource(id = R.string.text_home_divider),
                    style = cap3Regular
                )
                Image(
                    modifier = Modifier.size(12.dp),
                    painter = painterResource(id = R.drawable.ic_home_like),
                    contentDescription = stringResource(id = R.string.description_home_notice_like_icon)
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    color = Font_B03,
                    style = cap3Regular,
                    text = data.likeCount.toString()
                )
                Spacer(modifier = Modifier.width(6.dp))
                Image(
                    modifier = Modifier.size(12.dp),
                    painter = painterResource(id = R.drawable.ic_home_views),
                    contentDescription = stringResource(id = R.string.description_home_notice_views_icon)
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    color = Font_B03,
                    style = cap3Regular,
                    text = data.viewCount.toString()
                )
            }
        }
        AsyncImage(
            modifier = Modifier
                .size(58.dp)
                .clip(RoundedCornerShape(5.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data.image)
                .crossfade(true)
                .build(),
            placeholder = null,
            contentDescription = stringResource(id = R.string.description_home_logo_img)
        )
    }
}