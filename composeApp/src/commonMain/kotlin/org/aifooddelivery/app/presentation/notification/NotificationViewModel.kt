package org.aifooddelivery.app.presentation.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.LocalDate
import org.aifooddelivery.app.data.model.NotificationItem
import org.aifooddelivery.app.data.model.NotificationType
import org.aifooddelivery.app.presentation.componets.groupByDate
import org.aifooddelivery.app.presentation.componets.setNotificationList


class NotificationViewModel : ViewModel() {
    private val _notifications = MutableStateFlow<List<NotificationItem>>(emptyList())
    val notifications: StateFlow<List<NotificationItem>> = _notifications.asStateFlow()
    val groupedNotifications = notifications
        .map { groupByDate(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    init {
        loadNotifications()
    }

     fun loadNotifications() {
        // This could later be injected from a repository use case
        _notifications.value = setNotificationList()
    }
}