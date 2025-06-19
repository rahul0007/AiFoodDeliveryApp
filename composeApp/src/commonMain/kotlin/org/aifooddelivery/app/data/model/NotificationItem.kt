package org.aifooddelivery.app.data.model

import kotlinx.datetime.LocalDate


enum class NotificationType {
    OFFER, ORDER_TAKEN, ORDER_CANCELED, MAIL, ACCOUNT, CARD
}
data class NotificationItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val type: NotificationType,
    val timestamp: LocalDate
)