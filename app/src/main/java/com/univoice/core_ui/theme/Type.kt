package com.univoice.core_ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.univoice.R

// Set of Material typography styles to start with
val Typography =
    Typography(
        bodyLarge =
        TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp,
        ),
    )

// FontFamily
val pretendardBold = FontFamily(Font(R.font.pretendard_bold, FontWeight.Bold))
val pretendardSemiBold = FontFamily(Font(R.font.pretendard_semibold, FontWeight.SemiBold))
val pretendardRegular = FontFamily(Font(R.font.pretendard_regular, FontWeight.Normal))

// Head1
val head1Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 32.sp,
    )
val head1Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 32.sp,
    )
val head1Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 32.sp,
    )

// Head2
val head2Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 29.sp,
    )
val head2Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 29.sp,
    )
val head2Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 29.sp,
    )

// Head3
val head3Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 26.sp,
    )
val head3Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 26.sp,
    )
val head3Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 26.sp,
    )

// Head4
val head4Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 23.sp,
    )
val head4Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 23.sp,
    )
val head4Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 23.sp,
    )

// Head5
val head5Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 20.sp,
    )
val head5Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 20.sp,
    )
val head5Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 20.sp,
    )

// Head6
val head6Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 18.sp,
    )
val head6Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 18.sp,
    )
val head6Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 18.sp,
    )

// Head7
val head7Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 16.sp,
    )
val head7Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 16.sp,
    )
val head7Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 16.sp,
    )

// Title1
val title1Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 23.sp,
    )
val title1Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 23.sp,
        lineHeight = 34.sp,
    )
val title1Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 23.sp,
    )

// Title2
val title2Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 20.sp,
    )
val title2Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 20.sp,
    )
val title2Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 20.sp,
    )

// Title3
val title3Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 18.sp,
    )
val title3Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 18.sp,
    )
val title3Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 18.sp,
        lineHeight = 23.sp,
    )

// Title4
val title4Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 16.sp,
    )
val title4Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 16.sp,
    )
val title4Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 16.sp,
    )

// Body1
val body1Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 15.sp,
    )
val body1Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 15.sp,
    )
val body1Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 15.sp,
        lineHeight = 24.sp,
    )

// Body2
val body2Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 14.sp,
    )
val body2Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 14.sp,
    )
val body2Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 14.sp,
        lineHeight = 21.sp,
    )

// Body3
val body3Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 13.sp,
    )
val body3Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 13.sp,
    )
val body3Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 13.sp,
    )

// Body4
val body4Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 12.sp,
    )
val body4Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 12.sp,
    )
val body4Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 12.sp,
        lineHeight = 14.sp,
    )

// Cap
val cap1Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 13.sp,
    )
val cap2Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 12.sp,
    )
val cap3Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 11.sp,
    )
val cap4Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 10.sp,
    )

// Button1
val button1Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 16.sp,
    )
val button1Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 16.sp,
    )
val button1Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 16.sp,
    )

// Button2
val button2Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 14.sp,
    )
val button2Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 14.sp,
    )
val button2Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 14.sp,
    )

// Button3
val button3Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 13.sp,
    )
val button3Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 13.sp,
    )
val button3Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 13.sp,
    )

// Button4
val button4Bold =
    TextStyle(
        fontFamily = pretendardBold,
        fontSize = 12.sp,
    )
val button4Semi =
    TextStyle(
        fontFamily = pretendardSemiBold,
        fontSize = 12.sp,
    )
val button4Regular =
    TextStyle(
        fontFamily = pretendardRegular,
        fontSize = 12.sp,
    )