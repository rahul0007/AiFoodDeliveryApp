package org.aifooddelivery.app.ui.screen.call

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import androidx.navigation.NavHostController
import org.aifooddelivery.app.presentation.chat.viewModel.ChatDetailViewModel
import org.koin.compose.viewmodel.koinViewModel

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.call_bg
import aifooddeliveryapp.composeapp.generated.resources.ic_call_close
import aifooddeliveryapp.composeapp.generated.resources.ic_video_call
import aifooddeliveryapp.composeapp.generated.resources.ic_video_call_mic
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
@Composable
fun CallScreen(rootNavController: NavHostController){
        val viewModel: ChatDetailViewModel = koinViewModel()
        CallScreenContent(
            userName = viewModel.userName.value,
            callDuration = viewModel.callDuration.value,
            onEndCall = { rootNavController.popBackStack() }
        )
}

@Composable
fun CallScreenContent(
    userName: String,
    callDuration: String,
    onEndCall: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(Res.drawable.call_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(185.dp)
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black)
                        )
                    )
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = userName,
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .background(Color(0x55FFFFFF), RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "\uD83D\uDD34 $callDuration",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_call_close),
                        contentDescription = "End Call",
                        modifier = Modifier
                            .size(50.dp)
                            .clickable { onEndCall() }
                    )
                    Image(
                        painter = painterResource(Res.drawable.ic_video_call),
                        contentDescription = "Video",
                        modifier = Modifier.size(50.dp)
                    )
                    Image(
                        painter = painterResource(Res.drawable.ic_video_call_mic),
                        contentDescription = "Mic",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }
    }
}