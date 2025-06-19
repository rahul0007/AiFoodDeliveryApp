package org.aifooddelivery.app.presentation.componets

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.allStringResources
import aifooddeliveryapp.composeapp.generated.resources.arrowforward
import aifooddeliveryapp.composeapp.generated.resources.btn_Skip
import aifooddeliveryapp.composeapp.generated.resources.btn_next
import aifooddeliveryapp.composeapp.generated.resources.img_burger
import aifooddeliveryapp.composeapp.generated.resources.next
import aifooddeliveryapp.composeapp.generated.resources.onboarding_one_subtitle
import aifooddeliveryapp.composeapp.generated.resources.onboarding_one_title
import aifooddeliveryapp.composeapp.generated.resources.onboarding_three_image
import aifooddeliveryapp.composeapp.generated.resources.onboarding_two_image
import aifooddeliveryapp.composeapp.generated.resources.progress_button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
@Composable
fun OnboardingContent(
    position: Int,
    onSkip: () -> Unit = {},
    onNext: () -> Unit = {},
    onDone: () -> Unit = {},
    nextContent: @Composable () -> Unit = {
        Text(
            text = stringResource(Res.string.btn_next),
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(end = 8.dp)
        )
        Image(
            painter = painterResource(Res.drawable.arrowforward),
            contentDescription = stringResource(Res.string.btn_next),
            modifier = Modifier.size(20.dp)
        )
    }
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val image = when (position) {
            0 -> painterResource(Res.drawable.img_burger)
            1 -> painterResource(Res.drawable.onboarding_two_image)
            else -> painterResource(Res.drawable.onboarding_three_image)
        }
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(60.dp)
                .clip(RoundedCornerShape(45.dp))
                .background(Color(0xFFFF7A00))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(45.dp))
                Text(
                    text = stringResource(Res.string.onboarding_one_title),
                    style = onboardingTitle.copy(color = Color.White)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = stringResource(Res.string.onboarding_one_subtitle),
                    style = onboardingSubtitle.copy(color = Color.White),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row(horizontalArrangement = Arrangement.Center) {
                    repeat(3) { index ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 3.dp)
                                .height(7.dp)
                                .width(26.dp)
                                .clip(RoundedCornerShape(50))
                                .background(
                                    if (index == position) Color.White else Color.White.copy(alpha = 0.5f)
                                )
                        )
                    }
                }

                if (position != 2) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(onClick = onSkip) {
                            Text(stringResource(Res.string.btn_Skip), style = onboardingButton.copy(color = Color.White))
                        }

                        Button(
                            onClick = onNext,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        ) {
                            nextContent()
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.height(25.dp))
                    Image(
                        painter = painterResource(Res.drawable.progress_button),
                        contentDescription =stringResource(Res.string.next),
                        modifier = Modifier.size(80.dp).clickable {
                            onDone.invoke()
                        }

                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}
