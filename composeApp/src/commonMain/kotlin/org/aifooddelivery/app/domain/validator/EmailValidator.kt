package org.aifooddelivery.app.domain.validator

object EmailValidator {
    private val regex = "^[A-Za-z](.*)([@]{1})(.+)(\\.)(.+)$".toRegex()
    fun validate(email: String): String? {
        return when {
            email.isBlank() -> "Email is required"
            !regex.matches(email) -> "Invalid email"
            else -> null
        }
    }
}