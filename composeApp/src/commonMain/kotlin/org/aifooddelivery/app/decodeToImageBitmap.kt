package org.aifooddelivery.app

import androidx.compose.ui.graphics.ImageBitmap

expect fun decodeToImageBitmap(bytes: ByteArray): ImageBitmap