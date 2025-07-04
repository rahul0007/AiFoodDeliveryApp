package org.aifooddelivery.app.presentation.auth.login

sealed class LoginIntent {
    data class EmailChanged(val email: String) : LoginIntent()
    data class PasswordChanged(val password: String) : LoginIntent()
    object TogglePasswordVisibility : LoginIntent()
    object ShowValidationErrors : LoginIntent()
    object LoginClicked : LoginIntent()
    object ResetState : LoginIntent()
}

data class LoginState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val isFormValid: Boolean = false,
    val loginResult: LoginResult = LoginResult.Idle
)

sealed class LoginResult {
    object Idle : LoginResult()
    object Success : LoginResult()
    data class Error(val message: String) : LoginResult()
}

sealed class LoginUiEffect {
    object Navigate : LoginUiEffect()
    data class ShowToast(val message: String) : LoginUiEffect()
}