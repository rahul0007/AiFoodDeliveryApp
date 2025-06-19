package org.aifooddelivery.app

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext



actual class PlatformActivityWrapper actual constructor(
    actual val activity: Any
)

@Composable
actual fun getPlatformActivity(): PlatformActivityWrapper? {
    val context = LocalContext.current
    return (context as? ComponentActivity)?.let { PlatformActivityWrapper(it) }
}

