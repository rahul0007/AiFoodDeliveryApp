package org.aifooddelivery.app

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private var toastContext: Context? = null

fun initToastContext(context: Context) {
    toastContext = context.applicationContext
}

actual fun showToast(message: String) {
    toastContext?.let {
        Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
    }
}