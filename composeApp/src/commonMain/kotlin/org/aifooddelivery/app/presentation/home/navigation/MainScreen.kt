package org.aifooddelivery.app.presentation.home.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.aifooddelivery.app.presentation.navigation.graphs.NavGraph
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import org.aifooddelivery.app.presentation.navigation.NavigationItem
import org.aifooddelivery.app.presentation.navigation.NavigationSideBar
import org.aifooddelivery.app.presentation.navigation.NewsBottomNavigation
import org.aifooddelivery.app.utils.navigationItemsLists

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainScreen() : Screen {
    @Composable
    override fun Content() {
        val windowSizeClass = calculateWindowSizeClass()
        val isMediumExpandedWWSC by remember(windowSizeClass) {
            derivedStateOf {
                windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
            }
        }
        val rootNavController = rememberNavController()
        val navBackStackEntry by rootNavController.currentBackStackEntryAsState()
        val currentRoute by remember(navBackStackEntry) {
            derivedStateOf {
                navBackStackEntry?.destination?.route
            }
        }
        val navigationItem by remember {
            derivedStateOf {
                navigationItemsLists.find {
                    it.route::class.qualifiedName == currentRoute
                }
            }
        }
        println("navigationItem${navigationItem?.title.toString()}")
        val isMainScreenVisible by remember(isMediumExpandedWWSC) {
            derivedStateOf {
                navigationItem != null
            }
        }
        val isBottomBarVisible by remember(isMediumExpandedWWSC) {
            derivedStateOf {
                if (!isMediumExpandedWWSC) {
                    navigationItem != null
                } else {
                    false
                }
            }
        }
        MainScaffold(
            rootNavController = rootNavController,
            currentRoute = currentRoute,
            isMediumExpandedWWSC = isMediumExpandedWWSC,
            isBottomBarVisible = isBottomBarVisible,
            isMainScreenVisible = isMainScreenVisible,
            onItemClick = { currentNavigationItem ->
                rootNavController.navigate(currentNavigationItem.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    rootNavController.graph.startDestinationRoute?.let { startDestinationRoute ->
                        // Pop up to the start destination, clearing the back stack
                        popUpTo(startDestinationRoute) {
                            // Save the state of popped destinations
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    // Restore state when re-selecting a previously selected item
                    restoreState = true
                }
            })
    }
}

@Composable
fun MainScaffold(
    rootNavController: NavHostController,
    currentRoute: String?,
    isMediumExpandedWWSC: Boolean,
    isBottomBarVisible: Boolean,
    isMainScreenVisible: Boolean,
    onItemClick: (NavigationItem) -> Unit,
) {

    // 👉 Debug current route
    LaunchedEffect(currentRoute) {
        println("✅ Current route: $currentRoute")
    }
    Row {
        AnimatedVisibility(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
            visible = isMediumExpandedWWSC && isMainScreenVisible,
            enter = slideInHorizontally(
                // Slide in from the left
                initialOffsetX = { fullWidth -> -fullWidth }
            ),
            exit = slideOutHorizontally(
                // Slide out to the right
                targetOffsetX = { fullWidth -> -fullWidth }
            )
        ) {
            NavigationSideBar(
                items = navigationItemsLists,
                currentRoute = currentRoute,
                onItemClick = { currentNavigationItem ->
                    onItemClick(currentNavigationItem)
                }
            )
        }
        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = isBottomBarVisible,
                    enter = slideInVertically(
                        // Slide in from the bottom
                        initialOffsetY = { fullHeight -> fullHeight }
                    ),
                    exit = slideOutVertically(
                        // Slide out to the bottom
                        targetOffsetY = { fullHeight -> fullHeight }
                    )
                ) {
                    NewsBottomNavigation(
                        items = navigationItemsLists,
                        currentRoute = currentRoute,
                        onItemClick = { currentNavigationItem ->
                            onItemClick(currentNavigationItem)
                        }
                    )
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                NavGraph(
                    rootNavController = rootNavController,
                )
            }
        }
    }
}