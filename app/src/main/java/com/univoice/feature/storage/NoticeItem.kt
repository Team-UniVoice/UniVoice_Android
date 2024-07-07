package com.univoice.feature.storage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.univoice.R

@Composable
fun NoticeItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 12.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "공지사항",
                Modifier
                    .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp),
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "명절 귀향 버스 수요 조사"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "06/26 ~ 07/01")
                VerticalDivider(
                    Modifier
                        .padding(horizontal = 8.dp)
                        .height(12.dp),
                    thickness = 1.dp
                )
                Image(
                    modifier = Modifier.size(12.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "like"
                )
                Text(text = "10")
                Spacer(modifier = Modifier.width(6.dp))
                Image(
                    modifier = Modifier.size(12.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null
                )
                Text(text = "10")
            }
        }
        Image(
            modifier = Modifier
                .size(58.dp)
                .clip(RoundedCornerShape(5.dp)),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "club image"
        )
    }
}

@Preview
@Composable
fun PreviewNoticeItem() {
    NoticeItem()
}