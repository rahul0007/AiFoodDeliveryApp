package org.aifooddelivery.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.aifooddelivery.app.core.di.appModule
import org.aifooddelivery.app.presentation.login.LoginScreen
import org.aifooddelivery.app.presentation.componets.AppNavigator
import org.aifooddelivery.app.presentation.Onboard.OnboardingScreen1
import org.aifooddelivery.app.presentation.home.navigation.MainScreen
import org.aifooddelivery.app.utils.DataStoreManager
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoin

@Composable
@Preview
fun App() {
    val currentScreen by remember { AppNavigator.currentScreen }
    /*startKoin {
        modules(appModule)
    }*/

    val dataStoreManager = remember { getKoin().get<DataStoreManager>() }
    LaunchedEffect(Unit) {
        val onboardingCompleted = dataStoreManager.isOnboardingCompleted()
        val loginCompleted = dataStoreManager.isLogin() // Assuming this was your intent
        val destination = when {
            loginCompleted -> MainScreen()
            onboardingCompleted -> LoginScreen()
            else -> OnboardingScreen1()
        }
        AppNavigator.navigate(destination)
    }
    MaterialTheme {
        currentScreen.Content()
    }
}