package org.aifooddelivery.app.presentation.componets

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.ic_cencle
import aifooddeliveryapp.composeapp.generated.resources.ic_creadit_card
import aifooddeliveryapp.composeapp.generated.resources.ic_msg
import aifooddeliveryapp.composeapp.generated.resources.ic_offer
import aifooddeliveryapp.composeapp.generated.resources.ic_orders
import aifooddeliveryapp.composeapp.generated.resources.ic_person
import androidx.compose.material3.Icon
import kotlinx.datetime.toLocalDateTime
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate

import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import org.aifooddelivery.app.data.model.NotificationItem
import org.aifooddelivery.app.data.model.NotificationType
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun NotificationIcon(type: NotificationType): Painter {
    return when (type) {
        NotificationType.OFFER -> painterResource(Res.drawable.ic_offer)
        NotificationType.ORDER_TAKEN -> painterResource(Res.drawable.ic_orders)
        NotificationType.ORDER_CANCELED -> painterResource(Res.drawable.ic_cencle)
        NotificationType.MAIL -> painterResource(Res.drawable.ic_msg)
        NotificationType.ACCOUNT -> painterResource(Res.drawable.ic_person)
        NotificationType.CARD -> painterResource(Res.drawable.ic_creadit_card)
    }
}
fun setNotificationList(): List<NotificationItem> {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    return listOf(
        NotificationItem(1, "30% Special Discount!", "Special promotion only valid today", NotificationType.OFFER, now),
        NotificationItem(2, "Your Order Has Been Taken by the Driver", "Recently", NotificationType.ORDER_TAKEN, now),
        NotificationItem(3, "Your Order Has Been Canceled", "19 Jun 2023", NotificationType.ORDER_CANCELED, now - DatePeriod(days = 2)),
        NotificationItem(4, "35% Special Discount!", "Special promotion only valid today", NotificationType.MAIL, now - DatePeriod(days = 1)),
        NotificationItem(5, "Account Setup Successful!", "Special promotion only valid today", NotificationType.ACCOUNT, now - DatePeriod(days = 1)),
        NotificationItem(6, "Special Offer! 60% Off", "Offer valid until 20 Nov 2022", NotificationType.OFFER, LocalDate(2022, 11, 20)),
        NotificationItem(7, "Credit Card Connected", "Special promotion only valid today", NotificationType.CARD, now - DatePeriod(days = 1))
    )
}

fun groupByDate(notifications: List<NotificationItem>): Map<String, List<NotificationItem>> {
    val now = Clock.System.now()
    val today = now.toLocalDateTime(TimeZone.currentSystemDefault()).date
    val yesterday = today.minus(DatePeriod(days = 1))

    return notifications.groupBy {
        val notificationDate = it.timestamp
        when (notificationDate) {
            today -> "Today"
            yesterday -> "Yesterday"
            else -> {
                // Format the date like "04 Jun 2025"
                val day = notificationDate.dayOfMonth.toString().padStart(2, '0')
                val month = notificationDate.month.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)
                val year = notificationDate.year
                "$day $month $year"
            }
        }
    }
}

