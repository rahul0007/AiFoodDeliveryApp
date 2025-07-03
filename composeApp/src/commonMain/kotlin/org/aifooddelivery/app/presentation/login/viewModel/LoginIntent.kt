package org.aifooddelivery.app.presentation.login.viewModel

sealed class LoginIntent {
    data class EmailChanged(val email: String) : LoginIntent()
    data class PasswordChanged(val password: String) : LoginIntent()
    object TogglePasswordVisibility : LoginIntent()
    object ShowValidationErrors : LoginIntent()
    object LoginClicked : LoginIntent()
    object ResetState : LoginIntent()
}