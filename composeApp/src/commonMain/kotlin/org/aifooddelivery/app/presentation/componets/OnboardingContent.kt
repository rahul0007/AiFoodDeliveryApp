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
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.aifooddelivery.app.utils.StringsManager
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = StringsManager.get("btn_next"),
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Image(
                painter = painterResource(Res.drawable.arrowforward),
                contentDescription = StringsManager.get("btn_next"),
                modifier = Modifier.size(20.dp)
            )
        }
    }
) {
    Box(modifier = Modifier.fillMaxSize()) {

        // 🔥 Fade-in/out animation for images
        val imageAlpha by animateFloatAsState(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
        )

        val image = when (position) {
            0 -> painterResource(Res.drawable.img_burger)
            1 -> painterResource(Res.drawable.onboarding_two_image)
            else -> painterResource(Res.drawable.onboarding_three_image)
        }

        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(imageAlpha)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(60.dp)
                .clip(RoundedCornerShape(45.dp))
                .background(Color(0xFFFF7A00))
                .offset(y = animateDpAsState(targetValue = 0.dp).value) // Slide-up animation
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(45.dp))

                // 🔥 Title with slight fade-in and slide-up
                AnimatedVisibility(visible = true, enter = fadeIn() + slideInVertically()) {
                    Text(
                        text = StringsManager.get("onboarding_one_title"),
                        style = onboardingTitle.copy(color = Color.White)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                AnimatedVisibility(visible = true, enter = fadeIn() + slideInVertically()) {
                    Text(
                        text = StringsManager.get("onboarding_one_subtitle"),
                        style = onboardingSubtitle.copy(color = Color.White),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(horizontalArrangement = Arrangement.Center) {
                    repeat(3) { index ->
                        val scale by animateFloatAsState(
                            targetValue = if (index == position) 1f else 1f,
                            animationSpec = tween(300)
                        )
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 3.dp)
                                .height(7.dp * scale)
                                .width(26.dp * scale)
                                .clip(RoundedCornerShape(50))
                                .background(
                                    if (index == position) Color.White else Color.White.copy(alpha = 0.5f)
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(if (position != 2) 50.dp else 25.dp))

                if (position != 2) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(onClick = onSkip) {
                            Text(
                                StringsManager.get("btn_Skip"),
                                style = onboardingButton.copy(color = Color.White)
                            )
                        }

                        // 🔥 Next button with scale animation on click
                        Button(
                            onClick = onNext,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        ) {
                            nextContent()
                        }
                    }
                } else {
                    val infiniteTransition = rememberInfiniteTransition()
                    val scale by infiniteTransition.animateFloat(
                        initialValue = 1f,
                        targetValue = 1.2f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(700, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        )
                    )

                    Image(
                        painter = painterResource(Res.drawable.progress_button),
                        contentDescription = "next",
                        modifier = Modifier
                            .size(80.dp * scale)
                            .clickable { onDone.invoke() }
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

