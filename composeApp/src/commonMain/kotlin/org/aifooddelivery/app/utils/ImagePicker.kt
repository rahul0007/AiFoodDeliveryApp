package org.aifooddelivery.app.utils

interface ImagePicker {
    suspend fun pickImageFromGallery(): ImageResult?
    suspend fun captureImageWithCamera(): ImageResult?
}

data class ImageResult(
    val name: String?,
    val bytes: ByteArray
)