package org.aifooddelivery.app.presentation.login.viewModel
sealed class LoginResult {
    object Success : LoginResult()
    object UserNotFound : LoginResult()
    object InvalidPassword : LoginResult()
}