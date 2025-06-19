package org.aifooddelivery.app.presentation.componets

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import cafe.adriel.voyager.core.screen.Screen
import org.aifooddelivery.app.presentation.Onboard.OnboardingScreen1


object AppNavigator {
    private val _screenStack = mutableStateListOf<Screen>(OnboardingScreen1())
    val screenStack: List<Screen> get() = _screenStack

    val currentScreen: State<Screen>
        get() = derivedStateOf { _screenStack.last() }

    fun navigate(screen: Screen) {
        _screenStack.add(screen)
    }


    fun goBack() {
        if (_screenStack.size > 1) {
            _screenStack.removeLast()
        }
    }
    fun resetTo(screen: Screen) {
        _screenStack.clear()
        _screenStack.add(screen)
    }
}