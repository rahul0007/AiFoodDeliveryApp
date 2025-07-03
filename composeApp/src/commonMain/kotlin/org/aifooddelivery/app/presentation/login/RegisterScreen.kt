package org.aifooddelivery.app.presentation.login

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import org.aifooddelivery.app.presentation.login.viewModel.RegisterViewModel
import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.btn_sign_in
import aifooddeliveryapp.composeapp.generated.resources.create_an_account_to_start_looking
import aifooddeliveryapp.composeapp.generated.resources.create_your_account
import aifooddeliveryapp.composeapp.generated.resources.dont_have_an_account
import aifooddeliveryapp.composeapp.generated.resources.email_address
import aifooddeliveryapp.composeapp.generated.resources.enter_email
import aifooddeliveryapp.composeapp.generated.resources.enter_user_name
import aifooddeliveryapp.composeapp.generated.resources.ic_apple
import aifooddeliveryapp.composeapp.generated.resources.ic_facebook
import aifooddeliveryapp.composeapp.generated.resources.ic_google
import aifooddeliveryapp.composeapp.generated.resources.or_sign_in_with
import aifooddeliveryapp.composeapp.generated.resources.password
import aifooddeliveryapp.composeapp.generated.resources.user_name
import aifooddeliveryapp.composeapp.generated.resources.visibility
import aifooddeliveryapp.composeapp.generated.resources.visibility_off
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.navigator.internal.BackHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.aifooddelivery.app.presentation.componets.AppNavigator
import org.aifooddelivery.app.presentation.componets.TermsAgreement
import org.aifooddelivery.app.presentation.login.viewModel.RegisterUiState
import org.aifooddelivery.app.showToast
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
@Composable
fun RegisterScreen()  {


        val viewModel: RegisterViewModel = koinInject()
        val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        val email by viewModel.email.collectAsState()
        val userName by viewModel.userName.collectAsState()
        val password by viewModel.password.collectAsState()
        val passwordVisible by viewModel.passwordVisible.collectAsState()
        val acceptedTerms by viewModel.acceptedTerms.collectAsState()
        val showErrors by viewModel.showErrors.collectAsState()
        val isFormValid = viewModel.isFormValid()
        val emailError = viewModel.emailError()?.let { stringResource(it) }
        val userNameError = viewModel.userNameError()?.let { stringResource(it) }
        val passwordError = viewModel.passwordError()?.let { stringResource(it) }
        LaunchedEffect(Unit) {
            viewModel.message.collect { msg ->
                showToast(msg)
            }
        }

        RegisterScreenContent(
            onBackPressed = {
                AppNavigator.goBack()
            },
            email = email,
            userName = userName,
            password = password,
            passwordVisible = passwordVisible,
            acceptedTerms = acceptedTerms,
            emailError = emailError,
            userNameError = userNameError,
            passwordError = passwordError,
            onEmailChange = viewModel::onEmailChange,
            onUserNameChange = viewModel::onUserNameChange,
            onPasswordChange = viewModel::onPasswordChange,
            onTogglePasswordVisibility = viewModel::togglePasswordVisibility,
            onAcceptedTermsChange = viewModel::setAcceptedTerms,
            onRegisterClick = {
                viewModel.showValidationErrors()
                if (isFormValid) {
                    viewModel.registerUser()

                }
            }
        )
    }

}

fun RegisterUiState.messageOrNull(): String? = when (this) {
    is RegisterUiState.Success -> this.message
    is RegisterUiState.Error -> this.message
    else -> null
}

@OptIn(InternalVoyagerApi::class)
@Composable
fun RegisterScreenContent(
    onBackPressed: () -> Unit,
    email: String,
    userName: String,
    password: String,
    passwordVisible: Boolean,
    acceptedTerms: Boolean,
    emailError: String?,
    userNameError: String?,
    passwordError: String?,
    onEmailChange: (String) -> Unit,
    onUserNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onAcceptedTermsChange: (Boolean) -> Unit,
    onRegisterClick: () -> Unit
) {
    val showErrors = remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current

    BackHandler(enabled = true) {
        onBackPressed.invoke()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 30.dp, vertical = 16.dp),

        ) {
        Column {
            Spacer(Modifier.height(70.dp))

            Text(
                text = stringResource(Res.string.create_your_account),
                lineHeight = 60.sp,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 50.sp
                ),
            )

            Spacer(Modifier.height(15.dp))
            Text(
                text = stringResource(Res.string.create_an_account_to_start_looking),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(Modifier.height(40.dp))

            // Email field
            Text(stringResource(Res.string.email_address))
            Spacer(Modifier.height(5.dp))
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                isError = showErrors.value && !emailError.isNullOrBlank(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text(stringResource(Res.string.enter_email)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                keyboardActions = KeyboardActions.Default,
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    errorBorderColor = Color.Red, // optional
                    focusedLabelColor = Color.Black, // optional
                    unfocusedLabelColor = Color.Black // optional
                )
            )
            if (showErrors.value && !emailError.isNullOrBlank()) {
                Text(
                    emailError!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            Spacer(Modifier.height(20.dp))

            // Username field
            Text(stringResource(Res.string.user_name))
            Spacer(Modifier.height(5.dp))
            OutlinedTextField(
                value = userName,
                onValueChange = onUserNameChange,
                isError = showErrors.value && !userNameError.isNullOrBlank(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text(stringResource(Res.string.enter_user_name)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                keyboardActions = KeyboardActions.Default,
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    errorBorderColor = Color.Red, // optional
                    focusedLabelColor = Color.Black, // optional
                    unfocusedLabelColor = Color.Black // optional
                )
            )
            if (showErrors.value && !userNameError.isNullOrBlank()) {
                Text(
                    userNameError!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            Spacer(Modifier.height(20.dp))

            // Password field
            Text(stringResource(Res.string.password))
            Spacer(Modifier.height(5.dp))
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                isError = showErrors.value && !passwordError.isNullOrBlank(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text(stringResource(Res.string.password)) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val iconRes =
                        if (passwordVisible) Res.drawable.visibility_off else Res.drawable.visibility
                    IconButton(onClick = onTogglePasswordVisibility) {
                        Icon(
                            painter = painterResource(iconRes),
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions.Default,
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    errorBorderColor = Color.Red, // optional
                    focusedLabelColor = Color.Black, // optional
                    unfocusedLabelColor = Color.Black // optional
                )
            )
            if (showErrors.value && !passwordError.isNullOrBlank()) {
                Text(
                    passwordError!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            Spacer(Modifier.height(12.dp))

            TermsAgreement(
                checked = acceptedTerms,
                onCheckedChange = onAcceptedTermsChange,
                onTermsClick = { uriHandler.openUri("https://policies.google.com/terms") },
                onPrivacyClick = { uriHandler.openUri("https://policies.google.com/terms") }
            )

            Spacer(Modifier.height(35.dp))

            // Register button
            Button(
                onClick = {
                    showErrors.value = true
                    onRegisterClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
            ) {
                Text(stringResource(Res.string.btn_sign_in), color = Color.White)
            }

            Spacer(Modifier.height(30.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(Res.string.or_sign_in_with),
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Divider(modifier = Modifier.weight(1f))
            }

            Spacer(Modifier.height(30.dp))

            val icons = listOf(
                Res.drawable.ic_google,
                Res.drawable.ic_facebook,
                Res.drawable.ic_apple
            )

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
                        modifier = Modifier
                            .size(70.dp)
                            .padding(12.dp)
                    )
                }
            }
        }

        // Bottom section with Sign In prompt
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 35.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(stringResource(Res.string.dont_have_an_account))
                Spacer(Modifier.width(4.dp))
                Text(
                    text = stringResource(Res.string.btn_sign_in),
                    color = Color(0xFFFF9800),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                    }
                )
            }
        }
    }
}

