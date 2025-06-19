package org.aifooddelivery.app.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import org.aifooddelivery.app.presentation.login.viewModel.ForgetPasswordViewModel
import org.koin.compose.koinInject
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.btn_continue
import aifooddeliveryapp.composeapp.generated.resources.email_address
import aifooddeliveryapp.composeapp.generated.resources.enter_email
import aifooddeliveryapp.composeapp.generated.resources.enter_your_email_address_info
import aifooddeliveryapp.composeapp.generated.resources.forgot_password
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.ModalBottomSheet
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.navigator.OnBackPressed
import cafe.adriel.voyager.navigator.internal.BackHandler
import org.aifooddelivery.app.presentation.componets.ReusableInputField
import org.aifooddelivery.app.presentation.componets.AppNavigator
import org.aifooddelivery.app.presentation.componets.HeaderLargeTextStyle
import org.jetbrains.compose.resources.stringResource


class ForgetPasswordScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel: ForgetPasswordViewModel = koinInject()
        val email by viewModel.email.collectAsState()
        val showErrors by viewModel.showErrors.collectAsState()
        val scope = rememberCoroutineScope()
        val emailError = viewModel.emailError()
        val isFormValid = viewModel.isFormValid()
        var showBottomSheet by remember { mutableStateOf(false) }
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

        ForgetPasswordScreenContent(
            onBackPressed = {
                AppNavigator.goBack()
            },
            email = email,
            emailError = emailError,
            onEmailChange = viewModel::onEmailChange,
            onSubmit = {
                viewModel.showValidationErrors()
                if (isFormValid) {
                    showBottomSheet = true
                }
            },
            showBottomSheet = showBottomSheet,
            sheetState = sheetState,
            onBottomSheetDismiss = { showBottomSheet = false },
            onContinueClick = { emailValue ->
                scope.launch {
                    sheetState.hide()
                    AppNavigator.navigate(OtpVerificationScreen(emailValue))
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, InternalVoyagerApi::class)
@Composable
fun ForgetPasswordScreenContent(
    onBackPressed: () -> Unit,
    email: String,
    emailError: String?,
    onEmailChange: (String) -> Unit,
    onSubmit: () -> Unit,
    showBottomSheet: Boolean,
    sheetState: SheetState,
    onBottomSheetDismiss: () -> Unit,
    onContinueClick: (String) -> Unit
) {

    BackHandler(enabled = true) {
        onBackPressed.invoke()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(Modifier.height(70.dp))
            Text(
                text = stringResource(Res.string.forgot_password),
                lineHeight = 60.sp,
                style = HeaderLargeTextStyle,
            )
            Spacer(Modifier.height(15.dp))
            Text(
                text = stringResource(Res.string.enter_your_email_address_info),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(Modifier.height(40.dp))

            ReusableInputField(
                label = stringResource(Res.string.email_address),
                value = email,
                onValueChange = onEmailChange,
                placeholder = stringResource(Res.string.enter_email),
                isError = emailError != null,
                errorText = emailError,
                keyboardType = KeyboardType.Email
            )

            Spacer(Modifier.height(100.dp))
            Button(
                onClick = onSubmit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
            ) {
                Text(stringResource(Res.string.btn_continue), color = Color.White)
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = onBottomSheetDismiss,
                sheetState = sheetState
            ) {
                ForgotPasswordBottomSheet(
                    email = email,
                    selectedOption = "whatsapp", // Or bind from state
                    onOptionSelected = {}, // Implement if needed
                    onContinueClicked = { onContinueClick(email) }
                )
            }
        }
    }
}
