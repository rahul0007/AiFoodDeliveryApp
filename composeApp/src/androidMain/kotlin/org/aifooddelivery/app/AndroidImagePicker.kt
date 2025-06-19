package org.aifooddelivery.app

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import org.aifooddelivery.app.utils.ImagePicker

class AndroidImagePicker(private val activity: ComponentActivity) : ImagePicker {

    private val galleryLauncher = activity.registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        // Handle selected image URI
    }

    private val cameraLauncher = activity.registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        // Handle captured bitmap
    }

    private val permissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Retry the last requested action
            when (lastRequested) {
                "gallery" -> pickImageFromGalleryInternal()
                "camera" -> captureImageWithCameraInternal()
            }
        } else {
            Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private var lastRequested: String? = null

    override fun pickImageFromGallery() {
        lastRequested = "gallery"
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            android.Manifest.permission.READ_MEDIA_IMAGES
        else
            android.Manifest.permission.READ_EXTERNAL_STORAGE

        if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
            pickImageFromGalleryInternal()
        } else {
            permissionLauncher.launch(permission)
        }
    }

    override fun captureImageWithCamera() {
        lastRequested = "camera"
        val permission = android.Manifest.permission.CAMERA
        if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
            captureImageWithCameraInternal()
        } else {
            permissionLauncher.launch(permission)
        }
    }

    private fun pickImageFromGalleryInternal() {
        galleryLauncher.launch("image/*")
    }

    private fun captureImageWithCameraInternal() {
        cameraLauncher.launch(null)
    }
}
