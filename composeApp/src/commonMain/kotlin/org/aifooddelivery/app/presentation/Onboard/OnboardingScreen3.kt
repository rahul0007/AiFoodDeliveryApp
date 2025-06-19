package org.aifooddelivery.app.presentation.Onboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.launch
import org.aifooddelivery.app.presentation.login.LoginScreen
import org.aifooddelivery.app.presentation.componets.AppNavigator
import org.aifooddelivery.app.presentation.componets.OnboardingContent
import org.aifooddelivery.app.utils.DataStoreManager
import org.koin.mp.KoinPlatform.getKoin


class OnboardingScreen3 : Screen {
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val dataStoreManager = remember { getKoin().get<DataStoreManager>() }

        OnboardingContent(2, {}, {
            println("OnboardingScreen4")
        }, {
            scope.launch {
                dataStoreManager.setOnboardingCompleted(true)
                AppNavigator.navigate(LoginScreen())
            }
        })
    }
}

