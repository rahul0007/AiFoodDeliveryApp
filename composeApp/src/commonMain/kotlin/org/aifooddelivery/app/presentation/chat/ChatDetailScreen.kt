package org.aifooddelivery.app.presentation.chat

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.back
import aifooddeliveryapp.composeapp.generated.resources.ic_call
import aifooddeliveryapp.composeapp.generated.resources.ic_check
import aifooddeliveryapp.composeapp.generated.resources.ic_emoji
import aifooddeliveryapp.composeapp.generated.resources.ic_send
import aifooddeliveryapp.composeapp.generated.resources.ic_upload
import aifooddeliveryapp.composeapp.generated.resources.img_user
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import cafe.adriel.voyager.core.screen.Screen
import org.aifooddelivery.app.presentation.chat.viewModel.ChatDetailViewModel
import org.aifooddelivery.app.theme.grayColor
import org.jetbrains.compose.resources.DrawableResource
import org.koin.compose.koinInject

@Composable
fun ChatDetailScreen(rootNavController: NavHostController) {
    val viewModel: ChatDetailViewModel = koinInject()
    ChatDetailScreenContent(
    state = viewModel.state.collectAsState().value ,
    onMessageChange = viewModel::onMessageChange,
    onSendClick = viewModel::onSendClick,
    onBack =
    { rootNavController.popBackStack() },
    onCall =
    { rootNavController.navigate("call_screen") }
    )
}

@Composable
fun ChatDetailScreenContent(
    state: ChatUiState,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit,
    onBack: () -> Unit,
    onCall: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .imePadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ChatTopBar("Stevano Clirover", onCall, onBack)

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                reverseLayout = false
            ) {
                items(state.chatItems.size) { index ->
                    when (val item = state.chatItems[index]) {
                        is ChatItems.Receiver -> ReceiverMessage(
                            avatarRes = Res.drawable.img_user,
                            name = item.name,
                            message = item.message,
                            time = item.time
                        )

                        is ChatItems.Sender -> SenderMessage(
                            message = item.message,
                            time = item.time
                        )
                    }
                }
            }

            ChatInputBar(
                message = state.currentMessage,
                onMessageChange = onMessageChange,
                onApplyClick = onSendClick
            )
        }
    }
}

@Composable
fun ChatTopBar(userName: String, oNCall: () -> Unit, onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(Res.drawable.back),
            contentDescription = "Back",
            modifier = Modifier.size(35.dp).clickable {
                onBack.invoke()
            }
        )
        Text(userName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Image(
            painter = painterResource(Res.drawable.ic_call),
            contentDescription = "call",
            modifier = Modifier.size(35.dp).clickable {
                oNCall.invoke()
            }
        )
    }
}

@Composable
fun ReceiverMessage(avatarRes: DrawableResource, name: String, message: String, time: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Row(verticalAlignment = Alignment.Top) {
            Image(
                painter = painterResource(avatarRes),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Box(
                    modifier = Modifier
                        .background(Color(0xFFF3F3F3), shape = RoundedCornerShape(10.dp))
                        .padding(10.dp)
                ) {
                    Text(message)
                }
            }
        }
        Text(time, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(start = 44.dp))
    }
}

@Composable
fun SenderMessage(message: String, time: String) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
        Box(
            modifier = Modifier
                .background(Color(0xFFFF9900), shape = RoundedCornerShape(10.dp))
                .padding(10.dp)
                .widthIn(max = 250.dp)
        ) {
            Text(text = message, color = Color.White)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(time, fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(Res.drawable.ic_check),
                contentDescription = null,
                tint = Color(0xFFFF9900),
                modifier = Modifier.size(14.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}


@Composable
fun ChatInputBar(
    message: String,
    onMessageChange: (String) -> Unit,
    onApplyClick: () -> Unit
) {

    Box(modifier = Modifier.padding(vertical = 15.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.weight(1f)) {
                OutlinedTextField(
                    value = message,
                    onValueChange = onMessageChange,
                    placeholder = {
                        Text("Type something...", color = grayColor)
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_emoji), // Replace with your actual icon
                            contentDescription = null,
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_upload), // Replace with your actual icon
                            contentDescription = null,
                        )
                    },
                    shape = RoundedCornerShape(12),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color(0xFFE0E0E0),
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Red
                    )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(Res.drawable.ic_send),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}
