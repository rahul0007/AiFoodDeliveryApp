package org.aifooddelivery.app.presentation.login.viewModel

sealed class RegisterUiState {
    object Idle : RegisterUiState()
    data class Success(val message: String) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
    object Loading : RegisterUiState()
}