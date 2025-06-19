package org.aifooddelivery.app.data.model

data class ChatMessage(val sender: Boolean, val name: String = "", val message: String, val time: String)
