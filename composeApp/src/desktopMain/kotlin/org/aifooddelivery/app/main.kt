package org.aifooddelivery.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.aifooddelivery.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinProject",
    ) {
        App()
    }
}