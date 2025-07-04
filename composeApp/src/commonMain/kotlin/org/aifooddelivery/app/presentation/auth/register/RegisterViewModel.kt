package org.aifooddelivery.app.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aifood.UserEntity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.aifooddelivery.app.domain.repository.UserRepository

import kotlinx.coroutines.flow.*

class RegisterViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state: StateFlow<RegisterUiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<RegisterUiEffect>()
    val effect: SharedFlow<RegisterUiEffect> = _effect.asSharedFlow()

    fun onIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.EmailChanged -> updateState {
                copy(
                    email = intent.email,
                    emailError = null
                )
            }

            is RegisterIntent.UserNameChanged -> updateState {
                copy(
                    userName = intent.userName,
                    userNameError = null
                )
            }

            is RegisterIntent.PasswordChanged -> updateState {
                copy(
                    password = intent.password,
                    passwordError = null
                )
            }

            RegisterIntent.togglePasswordVisibility -> updateState { copy(passwordVisible = !passwordVisible) }
            RegisterIntent.acceptedTerms -> updateState { copy(acceptedTerms = !acceptedTerms) }
            RegisterIntent.ShowValidationErrors -> validateInputs()
        }
    }

    private fun validateInputs() {
        val current = _state.value
        val emailError = validateEmail(current.email)
        val userNameError = validateUserName(current.userName)
        val passwordError = validatePassword(current.password)
        val isFormValid = listOf(
            emailError,
            userNameError,
            passwordError
        ).all { it == null } && current.acceptedTerms

        updateState {
            copy(
                emailError = emailError,
                userNameError = userNameError,
                passwordError = passwordError,
                isFromValid = isFormValid
            )
        }

        if (!isFormValid) {
            launchEffect(RegisterUiEffect.ShowToast("Please fix the errors and accept Terms."))
        } else {
            registerUser()
        }
    }

    fun registerUser() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }

            val userExists = userRepository.isEmailExists(_state.value.email)
            if (userExists) {
                launchEffect(RegisterUiEffect.ShowToast("Email already exists"))
            } else {
                val success = userRepository.registerUser(
                    UserEntity(
                        id = 0,
                        username = _state.value.userName,
                        email = _state.value.email,
                        password = _state.value.password
                    )
                )
                if (success) {
                    launchEffect(RegisterUiEffect.ShowToast("Registration Successful"))
                    launchEffect(RegisterUiEffect.NavigateLogin)
                } else {
                    launchEffect(RegisterUiEffect.ShowToast("Registration failed"))
                }
            }

            updateState { copy(isLoading = false) }
        }
    }

    private fun validateEmail(email: String) = when {
        email.isBlank() -> "Email is required"
        !Regex("^[A-Za-z](.*)([@]{1})(.+)(\\.)(.+)").matches(email) -> "Invalid email format"
        else -> null
    }

    private fun validateUserName(name: String) = when {
        name.isBlank() -> "User name is required"
        name.length < 4 -> "User name too short"
        else -> null
    }

    private fun validatePassword(password: String) = when {
        password.isBlank() -> "Password is required"
        password.length < 6 -> "Password too short"
        else -> null
    }

    private fun updateState(reducer: RegisterUiState.() -> RegisterUiState) {
        _state.update { it.reducer() }
    }

    private fun launchEffect(effect: RegisterUiEffect) {
        viewModelScope.launch { _effect.emit(effect) }
    }
}
