package org.aifooddelivery.app

import android.os.Build
import org.aifooddelivery.app.utils.ImagePicker

// androidMain/src/AndroidImagePicker.kt

import android.Manifest
import android.app.Activity
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.suspendCancellableCoroutine
import org.aifooddelivery.app.utils.ImageResult
import java.io.ByteArrayOutputStream
import kotlin.coroutines.resume

class AndroidImagePicker(
    private val launchGallery: () -> Unit,
    private val launchCamera: () -> Unit,
    private val requestPermission: (String) -> Unit,
    private val checkPermission: (String) -> Boolean,
) : ImagePicker {

    override suspend fun pickImageFromGallery(): ImageResult? {
        return suspendCancellableCoroutine { continuation ->
            lastRequested = "gallery"
            if (checkPermission(requiredGalleryPermission())) {
                launchGallery()
            } else {
                requestPermission(requiredGalleryPermission())
            }

            onImageSelectedListener = { continuation.resume(it) }
        }
    }

    override suspend fun captureImageWithCamera(): ImageResult? {
        return suspendCancellableCoroutine { continuation ->
            lastRequested = "camera"
            if (checkPermission(Manifest.permission.CAMERA)) {
                launchCamera()
            } else {
                requestPermission(Manifest.permission.CAMERA)
            }

            onImageSelectedListener = { continuation.resume(it) }
        }
    }

    private fun requiredGalleryPermission(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            Manifest.permission.READ_MEDIA_IMAGES
        else
            Manifest.permission.READ_EXTERNAL_STORAGE
    }

    companion object {
        var onImageSelectedListener: ((ImageResult?) -> Unit)? = null
        var lastRequested: String? = null
    }
}

