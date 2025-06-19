package org.aifooddelivery.app.presentation.chat

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.ic_check
import aifooddeliveryapp.composeapp.generated.resources.ic_uncheck
import aifooddeliveryapp.composeapp.generated.resources.img_user
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import org.aifooddelivery.app.data.model.ChatListUiState
import org.aifooddelivery.app.presentation.chat.viewModel.ChatListViewModel
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.aifooddelivery.app.data.model.ChatItem
import org.aifooddelivery.app.theme.blackColor
import org.aifooddelivery.app.theme.contanerColor2
import org.aifooddelivery.app.theme.grayColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun ChatListScreen(
    rootNavController: NavController,
    ) {
    val viewModel: ChatListViewModel = koinViewModel()
    val uiState = viewModel.state.collectAsState().value
    ChatListScreenContent(uiState = uiState, onChatClick = {
        rootNavController.navigate("chat_detail_screen")
    })
}

@Composable
fun ChatListScreenContent(
    uiState: ChatListUiState,
    onChatClick: (ChatItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(contanerColor2)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Chat List",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "All Message",
            fontSize = 16.sp,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(uiState.chats) { chat ->
                ChatListItem(chat = chat, onClick = { onChatClick(chat) })
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ChatListItem(chat: ChatItem, onClick: (ChatItem) -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke(chat)
            }
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp, start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.img_user),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = chat.name,
                    fontWeight = FontWeight.Bold,
                    color = blackColor,
                    fontSize = 18.sp
                )
                Text(
                    text = chat.message,
                    fontSize = 14.sp,
                    color = grayColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(text = chat.time, fontSize = 12.sp, color = Color.Gray)

                if (chat.read) {
                    Image(
                        painter = painterResource(Res.drawable.ic_check),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                } else {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(24.dp) // smaller badge size
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.ic_uncheck),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                        )

                        Text(
                            text = "3", // unread count
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(5.dp))

        }
    }
}

