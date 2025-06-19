package org.aifooddelivery.app.presentation.chat

import org.aifooddelivery.app.data.model.ChatMessage

data class ChatUiState(
    val chatItems: List<ChatItems> = listOf(
        ChatItems.Receiver("Stevano Clirover", "Just to order", "09:00"),
        ChatItems.Sender("Okay, for what level of spiciness?", "09:15"),
        ChatItems.Receiver("Stevano Clirover", "Okay, Wait a minute 🙏", "09:00"),
        ChatItems.Sender("Okay, I'm waiting 🙃↑↑", "09:15")
    ),
    val currentMessage: String = ""
)