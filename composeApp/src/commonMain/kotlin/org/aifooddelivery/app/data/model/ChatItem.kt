package org.aifooddelivery.app.data.model

data class ChatItem(
    val name: String,
    val message: String,
    val time: String,
    val read: Boolean
)
data class ChatListUiState(
    val chats: List<ChatItem> = emptyList()
)