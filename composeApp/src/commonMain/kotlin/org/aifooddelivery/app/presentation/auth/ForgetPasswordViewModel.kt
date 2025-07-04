package org.aifooddelivery.app.presentation.auth

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.email_is_required
import aifooddeliveryapp.composeapp.generated.resources.onboarding_one_subtitle
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.stringResource

class ForgetPasswordViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _showErrors = MutableStateFlow(false)
    val showErrors: StateFlow<Boolean> = _showErrors

    fun onEmailChange(value: String) {
        _email.value = value
    }

    fun showValidationErrors() {
        _showErrors.value = true
    }

    fun isFormValid(): Boolean {
        return emailRegex.matches(_email.value.trim())
    }

    @Composable
    fun emailError(): String? {
        val value = _email.value.trim()
        return when {
            !_showErrors.value -> null
            value.isBlank() -> stringResource(Res.string.email_is_required)
            !emailRegex.matches(value) -> stringResource(Res.string.onboarding_one_subtitle)
            else -> null
        }
    }

    private val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.+)(\\.)(.+)")
}
