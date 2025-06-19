package org.aifooddelivery.app.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.aifooddelivery.app.presentation.chat.ChatDetailScreen
import org.aifooddelivery.app.presentation.componets.setNotificationList
import org.aifooddelivery.app.presentation.navigation.Route
import org.aifooddelivery.app.presentation.chat.ChatListScreen
import org.aifooddelivery.app.presentation.home.HomeScreen
import org.aifooddelivery.app.presentation.notification.NotificationScreen
import org.aifooddelivery.app.presentation.productDetails.ProductDetailScreen
import org.aifooddelivery.app.ui.screen.call.CallScreen
import org.aifooddelivery.app.presentation.order.CartScreen
import org.aifooddelivery.app.presentation.order.PaymentScreen
import org.aifooddelivery.app.presentation.profile.AddCardScreen
import org.aifooddelivery.app.presentation.profile.DeliveryTrackingScreen
import org.aifooddelivery.app.presentation.profile.ExtraCardScreen
import org.aifooddelivery.app.presentation.profile.HelpCenterScreen
import org.aifooddelivery.app.presentation.profile.PersonalDataScreen
import org.aifooddelivery.app.presentation.profile.ProfileScreen
import org.aifooddelivery.app.presentation.profile.SettingsScreen
import org.aifooddelivery.app.presentation.search.SearchScreen

/**
 * Created 28-02-2024 at 03:04 pm
 */
@Composable
fun NavGraph(
    rootNavController: NavHostController,
) {
    NavHost(
        navController = rootNavController,
        startDestination = Route.Home,
    ) {
        composable<Route.Home> {
            HomeScreen(rootNavController = rootNavController)
        }
        composable<Route.Cart> {
            CartScreen(rootNavController = rootNavController)
        }
        composable<Route.Chat> {
            ChatListScreen(rootNavController = rootNavController)
        }
        composable<Route.Profile> {
            ProfileScreen(rootNavController = rootNavController)
        }

        composable("notification_screen") {
            NotificationScreen(rootNavController)
        }
        composable("product_detail_screen") {
           ProductDetailScreen(rootNavController)
        }

        composable("settings_screen") {
            SettingsScreen(rootNavController)
        }
        composable("help_center_screen") {
            HelpCenterScreen(rootNavController)
        }
        composable("personal_data_screen") {
            PersonalDataScreen(
                "Albert Stevano Bajefski",
                "19/06/1999",
                "Male",
                "+1 325-433-7656",
                "albertstevano@gmail.com",
                {},
                rootNavController
            )
        }

        composable("extra_card") {
            ExtraCardScreen(rootNavController)
        }

        composable("add_card") {
            AddCardScreen(rootNavController)
        }
        composable("chat_detail_screen") {
            ChatDetailScreen(rootNavController)
        }

        composable("search_screen") {
            SearchScreen(rootNavController)
        }
        composable("payment_screen") {
            PaymentScreen(rootNavController)
        }
        composable("call_screen") {
            CallScreen(rootNavController)
        }
        composable("delivery_tracking_screen") {
            DeliveryTrackingScreen(rootNavController)
        }
    }
}



