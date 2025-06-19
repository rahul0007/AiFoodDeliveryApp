package org.aifooddelivery.app.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Home : Route

    @Serializable
    data object Cart : Route

    @Serializable
    data object Chat : Route

    @Serializable
    data object Profile : Route



}