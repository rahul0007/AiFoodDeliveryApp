package org.aifooddelivery.app

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import org.aifooddelivery.app.utils.ImagePicker
import org.aifooddelivery.app.utils.ImageResult
import java.io.ByteArrayOutputStream

@Composable
actual fun rememberImagePicker(): ImagePicker {
    val context = LocalContext.current as ComponentActivity
    val contentResolver = context.contentResolver

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let { selectedUri ->
                val bytes = contentResolver.openInputStream(selectedUri)?.readBytes()
                val name = selectedUri.lastPathSegment ?: "gallery.jpg"
                AndroidImagePicker.onImageSelectedListener?.invoke(
                    ImageResult(
                        name,
                        bytes ?: ByteArray(0)
                    )
                )
            } ?: AndroidImagePicker.onImageSelectedListener?.invoke(null)
        }
    )

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
            bitmap?.let {
                val stream = ByteArrayOutputStream()
                it.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                AndroidImagePicker.onImageSelectedListener?.invoke(
                    ImageResult("camera.jpg", stream.toByteArray())
                )
            } ?: AndroidImagePicker.onImageSelectedListener?.invoke(null)
        }

    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                when (AndroidImagePicker.lastRequested) {
                    "gallery" -> galleryLauncher.launch("image/*")
                    "camera" -> cameraLauncher.launch(null)
                }
            } else {
                Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                AndroidImagePicker.onImageSelectedListener?.invoke(null)
            }
        }

    return remember {
        AndroidImagePicker(
            launchGallery = { galleryLauncher.launch("image/*") },
            launchCamera = { cameraLauncher.launch(null) },
            requestPermission = { permissionLauncher.launch(it) },
            checkPermission = { perm ->
                ContextCompat.checkSelfPermission(
                    context,
                    perm
                ) == PackageManager.PERMISSION_GRANTED
            }
        )
    }
}
