package org.aifooddelivery.app.presentation.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.aifooddelivery.app.theme.grayColor
import org.aifooddelivery.app.theme.primaryLight
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewsBottomNavigation(
    items: List<NavigationItem>,
    currentRoute: String?,
    onItemClick: (NavigationItem) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color.White
    ) {
        items.forEach { navigationItem ->
            val isSelected = navigationItem.route::class.qualifiedName == currentRoute

            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemClick(navigationItem) },
                icon = {
                    Icon(
                        painter = painterResource(navigationItem.icon),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                },
                label = {
                    Text(stringResource(navigationItem.title))
                },alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Color(0xFFFF9900),
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color(0xFFFF9900),
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}