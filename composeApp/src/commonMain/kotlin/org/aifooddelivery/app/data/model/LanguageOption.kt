package org.aifooddelivery.app.data.model

data class LanguageOption(
    val name: String,        // Display name: "English (US)", "Indonesia", etc.
    val code: String,        // Language code: "en", "id", "th", "zh"
    val isSelected: Boolean  // Whether this language is currently selected
)