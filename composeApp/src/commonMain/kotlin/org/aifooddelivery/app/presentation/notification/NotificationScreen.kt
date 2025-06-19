package org.aifooddelivery.app.presentation.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.aifooddelivery.app.data.model.NotificationItem
import org.aifooddelivery.app.theme.blackColor
import org.aifooddelivery.app.theme.deviderColor
import org.aifooddelivery.app.theme.grayColor
import org.aifooddelivery.app.presentation.componets.HomeToolbar
import org.aifooddelivery.app.presentation.componets.NotificationIcon
import org.aifooddelivery.app.presentation.componets.groupByDate
import org.koin.compose.koinInject

@Composable
fun NotificationScreen(rootNavController: NavHostController) {
    val viewModel: NotificationViewModel = koinInject()
    val grouped = groupByDate(viewModel.notifications.value)
    Scaffold(
        topBar = {
            HomeToolbar(
                true,
                callBackButton = {
                    rootNavController.popBackStack()
                },
                title = "Notification"
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            grouped.forEach { (dateHeader, items) ->
                item {
                    SectionHeader(dateHeader)
                }
                items(items) { notification ->
                    NotificationCard(notification)

                }
                item {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        color = deviderColor,
                        thickness = 3.dp
                    )
                }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 22.sp,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        color = grayColor
    )
}

@Composable
fun NotificationCard(notification: NotificationItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Icon(
            painter = NotificationIcon(notification.type),
            contentDescription = null,
            tint = Color.Unspecified, // Important if original icon color should be preserved
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape) // Makes it rounded
                .background(Color.LightGray) // Optional background
            // Optional inner padding
        )
        Spacer(Modifier.width(15.dp))
        Column {
            Text(
                notification.title,
                style = MaterialTheme.typography.bodyLarge,
                color = blackColor,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                notification.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = grayColor,
                fontSize = 12.sp
            )
        }
    }
}
