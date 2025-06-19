package org.aifooddelivery.app.domain.validator

object PasswordValidator {
    fun validate(password: String): String? {
        return when {
            password.isBlank() -> "Password is required"
            password.length < 6 -> "Password too short"
            else -> null
        }
    }
}