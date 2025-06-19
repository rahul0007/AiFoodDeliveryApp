package org.aifooddelivery.app.presentation.login.viewModel

import aifooddeliveryapp.composeapp.generated.resources.Res
import aifooddeliveryapp.composeapp.generated.resources.email_is_required
import aifooddeliveryapp.composeapp.generated.resources.invalid_email
import aifooddeliveryapp.composeapp.generated.resources.password_is_required
import aifooddeliveryapp.composeapp.generated.resources.password_to_short
import aifooddeliveryapp.composeapp.generated.resources.user_name_is_required
import aifooddeliveryapp.composeapp.generated.resources.user_name_too_short
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aifood.UserEntity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.aifooddelivery.app.domain.repository.UserRepository
import org.aifooddelivery.app.presentation.login.viewModel.RegisterUiState.*
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

class RegisterViewModel (private val userRepository: UserRepository): ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email
    private val _message = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val message: SharedFlow<String> = _message
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible: StateFlow<Boolean> = _passwordVisible

    private val _acceptedTerms = MutableStateFlow(false)
    val acceptedTerms: StateFlow<Boolean> = _acceptedTerms

    private var _showErrors = MutableStateFlow(false)


    val showErrors: StateFlow<Boolean> = _showErrors


    private val emailRegex = "^[A-Za-z](.*)([@]{1})(.+)(\\.)(.+)".toRegex()

    fun onEmailChange(value: String) {
        _email.value = value
    }

    fun onUserNameChange(value: String) {
        _userName.value = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }

    fun setAcceptedTerms(value: Boolean) {
        _acceptedTerms.value = value
    }

    fun showValidationErrors() {
        _showErrors.value = true
    }

    fun isFormValid(): Boolean {
        return validateEmail(_email.value) &&
                validateUserName(_userName.value) &&
                validatePassword(_password.value) &&
                _acceptedTerms.value
    }

    fun emailError(): StringResource? {
        if (!_showErrors.value) return null
        return when {
            _email.value.isBlank() -> Res.string.email_is_required
            !validateEmail(_email.value) -> Res.string.invalid_email
            else -> null
        }
    }

    fun userNameError(): StringResource? {
        if (!_showErrors.value) return null
        return when {
            _userName.value.isBlank() -> Res.string.user_name_is_required
            _userName.value.length < 4 -> Res.string.user_name_too_short
            else -> null
        }
    }

    @Composable
    fun passwordError(): StringResource? {
        if (!_showErrors.value) return null
        return when {
            _password.value.isBlank() -> Res.string.password_is_required
            _password.value.length < 6 ->  Res.string.password_to_short
            else -> null
        }
    }

    private fun validateEmail(input: String) = input.isNotBlank() && emailRegex.matches(input)
    private fun validateUserName(input: String) = input.length >= 4
    private fun validatePassword(input: String) = input.length >= 6

    private val _registerUiState = MutableStateFlow<RegisterUiState>(Idle)
    val registerUiState: StateFlow<RegisterUiState> = _registerUiState

    fun registerUser() {
        viewModelScope.launch {
            if (userRepository.isEmailExists(email.value)) {
                emitMessage("Email already exists")
            } else {
                val result = userRepository.registerUser(
                    UserEntity(0, userName.value, email.value, password.value)
                )
                emitMessage(if (result) "Registration Success" else "Registration failed")
            }
        }
    }

    fun RegisterUiState.messageOrNull(): String? = when (this) {
        is RegisterUiState.Success -> this.message
        is RegisterUiState.Error -> this.message
        else -> null
    }


    private suspend fun emitMessage(msg: String) {
        _message.emit(msg)
    }
}
