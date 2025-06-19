package org.aifooddelivery.app.presentation.componets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.aifooddelivery.app.theme.blackColor
import org.aifooddelivery.app.theme.grayColor


val onboardingTitle: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 35.sp,
        lineHeight = 40.sp,
        textAlign = TextAlign.Center
    )

val onboardingSubtitle: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        textAlign = TextAlign.Center
    )

val onboardingButton: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    )

val HeaderLargeTextStyle = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 40.sp,
    lineHeight = 60.sp,
    color = blackColor // You can change this to any color you want
)


val HeaderMediumStyle = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 30.sp,
    lineHeight = 50.sp,
    color = blackColor
)

val InfoMediumTextStyle = TextStyle(
    fontSize = 12.sp,
    lineHeight = 10.sp
)
val InfoTextStyle = TextStyle(
    fontSize = 16.sp,
    lineHeight = 20.sp,
    color = grayColor
)

val commonButtonTextStyle = TextStyle(
    fontSize = 20.sp,
    color = Color.White,
    textAlign = TextAlign.Center,
)