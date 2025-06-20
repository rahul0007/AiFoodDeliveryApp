package org.aifooddelivery.app

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap


actual fun decodeToImageBitmap(bytes: ByteArray): ImageBitmap {
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size).asImageBitmap()
}