package org.aifooddelivery.app.utils
import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.cart
import aifooddeliveryapp.composeapp.generated.resources.chat
import aifooddeliveryapp.composeapp.generated.resources.home
import aifooddeliveryapp.composeapp.generated.resources.ic_cart
import aifooddeliveryapp.composeapp.generated.resources.ic_chat
import aifooddeliveryapp.composeapp.generated.resources.ic_home
import aifooddeliveryapp.composeapp.generated.resources.ic_profile
import aifooddeliveryapp.composeapp.generated.resources.profile
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.ui.unit.Dp
import org.aifooddelivery.app.presentation.navigation.NavigationItem
import org.aifooddelivery.app.presentation.navigation.Route
const val BASE_URL = "https://newsapi.org/v2/"
const val DB_Name = "myNewsDB"
const val dataStoreFileName = "setting.preferences_pb"

val navigationItemsLists = listOf(
    NavigationItem(
        icon = Res.drawable.ic_home,
        title = Res.string.home,
        route = Route.Home
    ),
    NavigationItem(
        icon = Res.drawable.ic_cart,
        title = Res.string.cart,
        route = Route.Cart,
    ),
    NavigationItem(
        icon = Res.drawable.ic_chat,
        title = Res.string.chat,
        route = Route.Chat,
    ),
    NavigationItem(
        icon = Res.drawable.ic_profile,
        title = Res.string.profile,
        route = Route.Profile,
    ),
)



enum class Type {
    Mobile, Desktop
}

data class Size(
    val width: Dp,
    val height: Dp
)
val FadeIn = fadeIn(animationSpec = tween(220, delayMillis = 90)) +
        scaleIn(
            initialScale = 0.92f,
            animationSpec = tween(220, delayMillis = 90)
        )
val FadeOut = fadeOut(animationSpec = tween(90))