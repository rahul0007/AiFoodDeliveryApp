package org.aifooddelivery.app.presentation.auth

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject
import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.confirm_password
import aifooddeliveryapp.composeapp.generated.resources.new_password
import aifooddeliveryapp.composeapp.generated.resources.password
import aifooddeliveryapp.composeapp.generated.resources.visibility
import aifooddeliveryapp.composeapp.generated.resources.visibility_off
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

import org.aifooddelivery.app.theme.primaryLight
import org.aifooddelivery.app.presentation.componets.CommonAppBar
import org.aifooddelivery.app.presentation.componets.HeaderLargeTextStyle
import org.aifooddelivery.app.presentation.componets.InfoTextStyle
import org.aifooddelivery.app.presentation.componets.commonButtonTextStyle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

class ResetPasswordScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: ResetPasswordViewModel = koinInject()
        ResetPasswordScreenContent(viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreenContent(viewModel: ResetPasswordViewModel) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp, end = 25.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        CommonAppBar({}, "Reset Password")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Reset Password", style = HeaderLargeTextStyle)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Your new password must be different from the previously used password",
            textAlign = TextAlign.Start, style = InfoTextStyle
        )
        Spacer(modifier = Modifier.height(35.dp))

        // New Password
        Text(stringResource(Res.string.new_password))
        Spacer(Modifier.height(5.dp))
        OutlinedTextField(
            value = viewModel.newPassword,
            onValueChange = { viewModel.newPassword = it },
            isError = viewModel.newPasswordError != null,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            placeholder = { Text(stringResource(Res.string.password)) },
            visualTransformation = if (viewModel.newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val iconRes =
                    if (viewModel.newPasswordVisible) Res.drawable.visibility_off else Res.drawable.visibility
                IconButton(onClick = {
                    viewModel.newPasswordVisible = !viewModel.newPasswordVisible
                }) {
                    Icon(painterResource(iconRes), null)
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )
        viewModel.newPasswordError?.let {
            Text(
                stringResource(it),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Confirm Password
        Text(stringResource(Res.string.confirm_password))
        Spacer(Modifier.height(5.dp))
        OutlinedTextField(
            value = viewModel.confirmPassword,
            onValueChange = { viewModel.confirmPassword = it },
            isError = viewModel.confirmPasswordError != null,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            placeholder = { Text(stringResource(Res.string.password)) },
            visualTransformation = if (viewModel.confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val iconRes =
                    if (viewModel.confirmPasswordVisible) Res.drawable.visibility_off else Res.drawable.visibility
                IconButton(onClick = {
                    viewModel.confirmPasswordVisible = !viewModel.confirmPasswordVisible
                }) {
                    Icon(painterResource(iconRes), null)
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )
        viewModel.confirmPasswordError?.let {
            Text(
                stringResource(it),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Continue Button
        Button(
            onClick = { viewModel.onSubmit() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = primaryLight)
        ) {
            Text(
                "Continue",
                modifier = Modifier.padding(vertical = 8.dp),
                style = commonButtonTextStyle
            )
        }
    }
    // Bottom Sheet
    if (viewModel.showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.showBottomSheet = false },
            sheetState = sheetState
        ) {
            ChangedPasswordBottomSheet(
                onContinueClicked = {
                    scope.launch {
                        sheetState.hide()
                        viewModel.showBottomSheet = false
                    }
                }
            )
        }
    }
}

