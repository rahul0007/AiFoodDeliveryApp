package org.aifooddelivery.app.presentation.home.navigation

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.ic_cart
import aifooddeliveryapp.composeapp.generated.resources.ic_chat
import aifooddeliveryapp.composeapp.generated.resources.ic_home
import aifooddeliveryapp.composeapp.generated.resources.ic_profile
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.aifooddelivery.app.theme.grayColor
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun BottomNavBar(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf("Home") }

    Row(
        modifier = modifier.background(Color.White).padding(vertical = 8.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem("Home", Res.drawable.ic_home, selectedItem == "Home") {
            selectedItem = "Home"
        }
        BottomNavItem("Cart", Res.drawable.ic_cart, selectedItem == "Cart") {
            selectedItem = "Cart"
        }
        BottomNavItem("Chat", Res.drawable.ic_chat, selectedItem == "Chat") {
            selectedItem = "Chat"
        }
        BottomNavItem(
            "Profile", Res.drawable.ic_profile, selectedItem == "Profile"
        ) { selectedItem = "Profile" }
    }
}

@Composable
fun BottomNavItem(
    label: String, iconRes: DrawableResource, isSelected: Boolean, onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { onClick() }) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = if (isSelected) Color(0xFFFF9800) else grayColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (isSelected) {
            Text(text = label, fontSize = 12.sp, color = Color(0xFFFF9800))
        }
    }
}

