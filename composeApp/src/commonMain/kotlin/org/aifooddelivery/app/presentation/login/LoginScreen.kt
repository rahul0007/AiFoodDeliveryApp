package org.aifooddelivery.app.presentation.login

import aifooddeliveryapp.composeapp.generated.resources.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.launch
import org.aifooddelivery.app.presentation.login.viewModel.LoginViewModel
import org.aifooddelivery.app.presentation.componets.AppNavigator
import org.aifooddelivery.app.presentation.componets.HeaderLargeTextStyle
import org.aifooddelivery.app.presentation.componets.ReusableInputField
import org.aifooddelivery.app.presentation.componets.ReusablePasswordField
import org.aifooddelivery.app.presentation.home.navigation.MainScreen
import org.aifooddelivery.app.presentation.login.viewModel.LoginResult
import org.aifooddelivery.app.showToast
import org.aifooddelivery.app.utils.DataStoreManager
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.mp.KoinPlatform.getKoin


class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: LoginViewModel = koinInject()
        val email by viewModel.email.collectAsState()
        val password by viewModel.password.collectAsState()
        val passwordVisible by viewModel.passwordVisible.collectAsState()
        val scope = rememberCoroutineScope()
        val dataStore = remember { getKoin().get<DataStoreManager>() }
        val showErrors by viewModel.showErrors.collectAsState() // 👈 ADD THIS LINE
        val isFormValid = viewModel.isFormValid()
        val loginState by viewModel.loginState.collectAsState()
        val isLoading by viewModel.isLoading.collectAsState()
        val emailError by remember(email, showErrors) { derivedStateOf { viewModel.emailError() } }
        val passwordError by remember(password, showErrors) { derivedStateOf { viewModel.passwordError() } }

        val keyboardController = LocalSoftwareKeyboardController.current
        LaunchedEffect(loginState) {
            when (loginState) {
                is LoginUiState.Success -> {
                    dataStore.setLoginCompleted(true)
                    dataStore.setUserEmail(viewModel.email.value)
                    AppNavigator.navigate(MainScreen())
                }

                is LoginUiState.Error -> {
                    println("LoginUiState")
                    val message = (loginState as LoginUiState.Error).message
                    showToast(message)
                    viewModel.resetLoginState()
                }

                else -> {}
            }
        }
        LaunchedEffect(Unit) {
            viewModel.message.collect { msg ->
                showToast(msg)
            }
        }
        LoginScreenContent(
            email = email,
            password = password,
            passwordVisible = passwordVisible,
            emailError = emailError,
            passwordError = passwordError,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onTogglePasswordVisibility = viewModel::togglePasswordVisibility,
            onForgotPasswordClick = { AppNavigator.navigate(ForgetPasswordScreen()) },
            onLoginClick = {
                keyboardController?.hide()
                viewModel.showValidationErrors()
                if (isFormValid) {
                    viewModel.loginWithValidation {
                        scope.launch {
                            dataStore.setLoginCompleted(true)
                            dataStore.setUserEmail(viewModel.email.value)
                            AppNavigator.navigate(MainScreen())
                        }
                    }
                }
            },
            onRegisterClick = { AppNavigator.navigate(RegisterScreen()) }, isLoading
        )
    }
}

