package org.aifooddelivery.app.presentation.auth.login

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
import org.aifooddelivery.app.presentation.auth.ForgetPasswordScreen
import org.aifooddelivery.app.presentation.auth.register.RegisterScreen
import org.aifooddelivery.app.presentation.componets.AppNavigator
import org.aifooddelivery.app.presentation.componets.HeaderLargeTextStyle
import org.aifooddelivery.app.presentation.componets.ReusableInputField
import org.aifooddelivery.app.presentation.componets.ReusablePasswordField
import org.aifooddelivery.app.presentation.home.navigation.MainScreen
import org.aifooddelivery.app.showToast
import org.aifooddelivery.app.utils.DataStoreManager
import org.aifooddelivery.app.utils.StringsManager
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.mp.KoinPlatform.getKoin


class LoginScreen : Screen {
    @Composable
    override fun Content() {
        var viewModel: LoginViewModel = koinInject()
        val state by viewModel.state.collectAsState()
        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is LoginUiEffect.ShowToast -> showToast(effect.message)
                    LoginUiEffect.Navigate -> {

                    }
                }
            }
        }

        LoginScreenContent(
            email = state.email,
            password = state.password,
            passwordVisible = state.passwordVisible,
            emailError = state.emailError,
            passwordError = state.passwordError,
            isLoading = state.isLoading,
            onEmailChange = { viewModel.onIntent(LoginIntent.EmailChanged(it)) },
            onPasswordChange = { viewModel.onIntent(LoginIntent.PasswordChanged(it)) },
            onTogglePasswordVisibility = { viewModel.onIntent(LoginIntent.TogglePasswordVisibility) },
            onForgotPasswordClick = { AppNavigator.navigate(ForgetPasswordScreen()) },
            onLoginClick = { viewModel.onIntent(LoginIntent.LoginClicked) },
            onRegisterClick = { AppNavigator.navigate(RegisterScreen()) }
        )

        // Handle Login Success Navigation
        if (state.loginResult is LoginResult.Success) {
            scope.launch {
                val dataStore = getKoin().get<DataStoreManager>()
                dataStore.setLoginCompleted(true)
                dataStore.setUserEmail(state.email)
                AppNavigator.navigate(MainScreen())
                viewModel.onIntent(LoginIntent.ResetState)
            }
        }
    }
}

@Composable
fun LoginScreenContent(
    email: String,
    password: String,
    passwordVisible: Boolean,
    emailError: String?,
    passwordError: String?,
    isLoading: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 30.dp, vertical = 16.dp),

            ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(Modifier.height(70.dp))
                Text(
                    text = StringsManager.get("login_to_your_account"),
                    lineHeight = 60.sp,
                    style = HeaderLargeTextStyle,
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = stringResource(Res.string.sign_in_your_account),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(Modifier.height(40.dp))

                ReusableInputField(
                    label = StringsManager.get("email_address"),
                    value = email,
                    onValueChange = onEmailChange,
                    placeholder = StringsManager.get("enter_email"),
                    isError = emailError != null,
                    errorText = emailError,
                    keyboardType = KeyboardType.Email
                )

                Spacer(Modifier.height(20.dp))

                ReusablePasswordField(
                    label = StringsManager.get("password"),
                    value = password,
                    onValueChange = onPasswordChange,
                    isVisible = passwordVisible,
                    onVisibilityToggle = onTogglePasswordVisibility,
                    isError = passwordError != null,
                    errorText = passwordError
                )

                Spacer(Modifier.height(28.dp))
                Text(
                    text = StringsManager.get("forgot_password"),
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { onForgotPasswordClick() },
                    color = Color(0xFFFF9800),
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(35.dp))
                Button(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
                ) {
                    Text(StringsManager.get("btn_sign_in"), color = Color.White)
                }

                Spacer(Modifier.height(30.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Divider(modifier = Modifier.weight(1f))
                    Text(
                        text = StringsManager.get("or_sign_in_with"),
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Divider(modifier = Modifier.weight(1f))
                }

                Spacer(Modifier.height(30.dp))
                val icons =
                    listOf(Res.drawable.ic_google, Res.drawable.ic_facebook, Res.drawable.ic_apple)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    icons.forEach { iconRes ->
                        Image(
                            painter = painterResource(iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(70.dp).padding(12.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(top = 35.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(StringsManager.get("dont_have_an_account"))
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = StringsManager.get("btn_register"),
                        color = Color(0xFFFF9800),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { onRegisterClick() }
                    )
                }
            }

            /*if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            }*/
        }

        // Loader overlay
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000)) // semi-transparent black
                    .wrapContentSize(Alignment.Center)
            ) {
                CircularProgressIndicator(
                    color = Color(0xFFFF9800),
                    strokeWidth = 3.dp,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}

//class LoginScreen : Screen {
//
//    @Composable
//    override fun Content() {
//        val viewModel: LoginViewModel = koinInject()
//        val email by viewModel.email.collectAsState()
//        val password by viewModel.password.collectAsState()
//        val passwordVisible by viewModel.passwordVisible.collectAsState()
//        val scope = rememberCoroutineScope()
//        val dataStore = remember { getKoin().get<DataStoreManager>() }
//        val showErrors by viewModel.showErrors.collectAsState() // 👈 ADD THIS LINE
//        val isFormValid = viewModel.isFormValid()
//        val loginState by viewModel.loginState.collectAsState()
//        val isLoading by viewModel.isLoading.collectAsState()
//        val emailError by remember(email, showErrors) { derivedStateOf { viewModel.emailError() } }
//        val passwordError by remember(password, showErrors) { derivedStateOf { viewModel.passwordError() } }
//
//        val keyboardController = LocalSoftwareKeyboardController.current
//        LaunchedEffect(loginState) {
//            when (loginState) {
//                is LoginUiState.Success -> {
//                    dataStore.setLoginCompleted(true)
//                    dataStore.setUserEmail(viewModel.email.value)
//                    AppNavigator.navigate(MainScreen())
//                }
//
//                is LoginUiState.Error -> {
//                    println("LoginUiState")
//                    val message = (loginState as LoginUiState.Error).message
//                    showToast(message)
//                    viewModel.resetLoginState()
//                }
//
//                else -> {}
//            }
//        }
//        LaunchedEffect(Unit) {
//            viewModel.message.collect { msg ->
//                showToast(msg)
//            }
//        }
//        LoginScreenContent(
//            email = email,
//            password = password,
//            passwordVisible = passwordVisible,
//            emailError = emailError,
//            passwordError = passwordError,
//            onEmailChange = viewModel::onEmailChange,
//            onPasswordChange = viewModel::onPasswordChange,
//            onTogglePasswordVisibility = viewModel::togglePasswordVisibility,
//            onForgotPasswordClick = { AppNavigator.navigate(ForgetPasswordScreen()) },
//            onLoginClick = {
//                keyboardController?.hide()
//                viewModel.showValidationErrors()
//                if (isFormValid) {
//                    viewModel.loginWithValidation {
//                        scope.launch {
//                            dataStore.setLoginCompleted(true)
//                            dataStore.setUserEmail(viewModel.email.value)
//                            AppNavigator.navigate(MainScreen())
//                        }
//                    }
//                }
//            },
//            onRegisterClick = { AppNavigator.navigate(RegisterScreen()) }, isLoading
//        )
//    }
//}

