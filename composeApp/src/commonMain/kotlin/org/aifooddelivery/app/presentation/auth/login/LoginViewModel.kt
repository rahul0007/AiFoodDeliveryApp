package org.aifooddelivery.app.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.aifooddelivery.app.domain.repository.UserRepository
import org.aifooddelivery.app.domain.usecase.LoginUseCase


class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val userRepository: UserRepository
) : ViewModel() {


    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()
    private val _effect = MutableSharedFlow<LoginUiEffect>()
    val effect: SharedFlow<LoginUiEffect> = _effect

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> {
                loginUseCase.invoke("","")
                _state.update { it.copy(email = intent.email, emailError = null) }
            }

            is LoginIntent.PasswordChanged -> {
                _state.update { it.copy(password = intent.password, passwordError = null) }
            }

            LoginIntent.TogglePasswordVisibility -> {
                _state.update { it.copy(passwordVisible = !it.passwordVisible) }
            }

            LoginIntent.ShowValidationErrors -> {
                val emailError = validateEmail(_state.value.email)
                val passwordError = validatePassword(_state.value.password)
                _state.update {
                    it.copy(
                        emailError = emailError,
                        passwordError = passwordError,
                        isFormValid = emailError == null && passwordError == null
                    )
                }
            }

            LoginIntent.LoginClicked -> {
                if (_state.value.isFormValid) {
                    performLogin()
                } else {
                    onIntent(LoginIntent.ShowValidationErrors)
                }
            }

            LoginIntent.ResetState -> {
                _state.value = LoginState()
            }
        }
    }

    private fun validateEmail(email: String): String? {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.+)(\\.)(.+)".toRegex()
        return when {
            email.isBlank() -> "Email is required"
            !emailRegex.matches(email) -> "Invalid email"
            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Password is required"
            password.length < 6 -> "Password too short"
            else -> null
        }
    }

    private fun performLogin() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result =
                userRepository.loginWithValidation(_state.value.email, _state.value.password)
            if (result == null) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        loginResult = LoginResult.Error("User not registered")
                    )
                }
                launchEffect(LoginUiEffect.ShowToast("User not registered"))
            } else if (result.password != _state.value.password) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        loginResult = LoginResult.Error("Invalid password")
                    )
                }
                launchEffect(LoginUiEffect.ShowToast("Invalid password"))

            } else {
                _state.update { it.copy(isLoading = false, loginResult = LoginResult.Success) }
                launchEffect(LoginUiEffect.ShowToast("Login Success"))

            }
        }
    }

    private fun launchEffect(effect: LoginUiEffect) {
        viewModelScope.launch { _effect.emit(effect) }
    }
}

/*
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

    */
/* fun isFormValid(): Boolean {
         return validatePassword(_password.value)
     }*//*


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

        CoroutineScope(Dispatchers.Main).launch {
            _isLoading.value = true
            val fetchedData = loginUseCase.invoke(_email.value, _password.value)
            if (fetchedData.isSuccess()) {
                _loginState.value = LoginUiState.Success
            } else {

                _loginState.value =
                    LoginUiState.Error(fetchedData.getErrorMessage() ?: "Unknown error")
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
*/
