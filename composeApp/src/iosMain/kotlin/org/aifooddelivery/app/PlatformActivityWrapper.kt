package org.aifooddelivery.app

import androidx.compose.runtime.Composable

actual class PlatformActivityWrapper {
    actual val activity: Any = TODO()
}

@Composable
actual fun getPlatformActivity(): PlatformActivityWrapper? = null