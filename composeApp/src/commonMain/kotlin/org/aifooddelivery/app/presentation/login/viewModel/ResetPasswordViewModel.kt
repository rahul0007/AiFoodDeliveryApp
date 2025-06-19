package org.aifooddelivery.app.presentation.login.viewModel

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.both_password
import aifooddeliveryapp.composeapp.generated.resources.password_is_required
import aifooddeliveryapp.composeapp.generated.resources.password_to_short
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.jetbrains.compose.resources.StringResource

class ResetPasswordViewModel : ViewModel() {
    var newPassword by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var showErrors by mutableStateOf(false)
    var showBottomSheet by mutableStateOf(false)

    var newPasswordVisible by mutableStateOf(false)
    var confirmPasswordVisible by mutableStateOf(false)

    private fun validatePassword(input: String) = input.length >= 6

    val newPasswordError: StringResource?
        get() = if (!showErrors) null
        else when {
            newPassword.isBlank() -> Res.string.password_is_required
            !validatePassword(newPassword) -> Res.string.password_to_short
            else -> null
        }

    val confirmPasswordError: StringResource?
        get() = if (!showErrors) null
        else if (newPassword != confirmPassword) Res.string.both_password else null

    val isFormValid: Boolean
        get() = validatePassword(newPassword) && newPassword == confirmPassword

    fun onSubmit() {
        showErrors = true
        if (isFormValid) {
            showBottomSheet = true
        }
    }
}