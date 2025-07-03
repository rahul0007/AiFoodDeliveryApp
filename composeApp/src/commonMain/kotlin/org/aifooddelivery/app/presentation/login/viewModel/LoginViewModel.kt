package org.aifooddelivery.app.presentation.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.aifooddelivery.app.domain.repository.UserRepository
import org.aifooddelivery.app.domain.usecase.LoginUseCase


class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val userRepository: UserRepository
) : ViewModel() {

    private val emailRegex = "^[A-Za-z](.*)([@]{1})(.+)(\\.)(.+)".toRegex()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible: StateFlow<Boolean> = _passwordVisible.asStateFlow()

    private val _showErrors = MutableStateFlow(false)
    val showErrors: StateFlow<Boolean> = _showErrors.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginState: StateFlow<LoginUiState> = _loginState

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError.asStateFlow()

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError.asStateFlow()


    fun showValidationErrors() {
        _showErrors.value = true
        _emailError.value = emailError()
        _passwordError.value = passwordError()
    }

    fun isFormValid(): Boolean {
        return validateEmail(_email.value) && validatePassword(_password.value)
    }


    fun emailError(): String? {
        println("showErrors = ${showErrors.value}, email = '${_email.value}'")
        if (!showErrors.value) return null
        if (_email.value.isBlank()) return "Email is required"
        if (!validateEmail(_email.value)) return "Invalid email"
        return null
    }

    fun passwordError(): String? {
        if (!_showErrors.value) return null
        return when {
            _password.value.isBlank() -> "Password is required"
            !validatePassword(_password.value) -> "Password too short"
            else -> null
        }
    }

    private fun validateEmail(email: String): Boolean = emailRegex.matches(email)
    private fun validatePassword(password: String): Boolean = password.length >= 6


    fun getLogin() {

        viewModelScope.launch {
            _isLoading.value = true
            val fetchedData = withContext(Dispatchers.IO) {
                loginUseCase.invoke(_email.value, _password.value)
            }
            if (fetchedData.isSuccess()) {
                _loginState.value = LoginUiState.Success
            } else {
                _loginState.value = LoginUiState.Error(fetchedData.getErrorMessage() ?: "Unknown error")
            }
            _isLoading.value = false
        }

    }

    fun resetLoginState() {
        _loginState.value = LoginUiState.Idle
    }

    private val _message = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val message: SharedFlow<String> = _message

    fun loginWithValidation(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val user = userRepository.loginWithValidation(_email.value, _password.value)
            when {
                user == null -> emitMessage("User not registered")
                user.password != _password.value -> emitMessage("Invalid password")
                else -> {
                    emitMessage("Login success")
                    onSuccess()
                }
            }
        }
    }

    private suspend fun emitMessage(msg: String) {
        _message.emit(msg)
    }
}

