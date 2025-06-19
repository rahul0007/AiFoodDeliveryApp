package org.aifooddelivery.app.data.model

@kotlinx.serialization.Serializable
data class LoginRequest(
    val username: String,
    val password: String
)