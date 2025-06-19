package org.aifooddelivery.app

import androidx.compose.runtime.Composable

// commonMain
expect class PlatformActivityWrapper(activity: Any) {
    val activity: Any
}

@Composable
expect fun getPlatformActivity(): PlatformActivityWrapper?
