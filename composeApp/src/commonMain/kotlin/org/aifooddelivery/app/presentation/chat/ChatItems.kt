package org.aifooddelivery.app.presentation.chat

sealed class ChatItems {
    data class Sender(val message: String, val time: String) : ChatItems()
    data class Receiver(val name: String, val message: String, val time: String) : ChatItems()
}