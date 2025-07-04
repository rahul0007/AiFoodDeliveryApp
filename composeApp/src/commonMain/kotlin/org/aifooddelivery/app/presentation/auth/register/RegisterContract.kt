package org.aifooddelivery.app.presentation.auth.register

sealed class RegisterIntent() {
    data class EmailChanged(val email: String) : RegisterIntent()
    data class PasswordChanged(val password: String) : RegisterIntent()
    data class UserNameChanged(val userName: String) : RegisterIntent()
    object togglePasswordVisibility : RegisterIntent()
    object acceptedTerms: RegisterIntent()
    object ShowValidationErrors : RegisterIntent()
}

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val userName: String = "",
    val message: String = "",
    val passwordVisible: Boolean = false,
    val acceptedTerms: Boolean = false,
    val showErrors: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val userNameError: String? = null,
    val isSuccess: Boolean = false,
    val isFromValid: Boolean = false
)

sealed class RegisterUiEffect {
    object NavigateLogin : RegisterUiEffect()
    data class ShowToast(val message: String) : RegisterUiEffect()
}