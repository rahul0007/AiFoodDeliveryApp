package org.aifooddelivery.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.aifooddelivery.app.utils.ImagePicker
import org.aifooddelivery.app.utils.ImageResult

@Composable
actual fun rememberImagePicker(): ImagePicker {
    return remember {
        IosImagePicker(
            viewControllerProvider = {
                UIApplication.sharedApplication.keyWindow?.rootViewController?.topMostController()
                    ?: error("No root ViewController found")
            }
        )
    }
}

// Helper to get top most visible UIViewController
fun UIViewController.topMostController(): UIViewController {
    return presentedViewController?.topMostController() ?: this
}


class IosImagePicker : NSObject(), ImagePicker, UIImagePickerControllerDelegateProtocol, UINavigationControllerDelegateProtocol {

    private var continuation: (suspendCancellableCoroutine<ImageResult?>.Continuation)? = null

    override suspend fun pickImageFromGallery(): ImageResult? {
        return launchPicker(UIImagePickerControllerSourceTypePhotoLibrary)
    }

    override suspend fun captureImageWithCamera(): ImageResult? {
        return launchPicker(UIImagePickerControllerSourceTypeCamera)
    }

    private suspend fun launchPicker(sourceType: UIImagePickerControllerSourceType): ImageResult? {
        return suspendCancellableCoroutine { cont ->
            val controller = UIImagePickerController()
            controller.sourceType = sourceType
            controller.delegate = this
            controller.allowsEditing = false

            continuation = cont

            val rootController = UIApplication.sharedApplication.keyWindow?.rootViewController
            rootController?.presentViewController(controller, animated = true, completion = null)

            cont.invokeOnCancellation {
                rootController?.dismissViewControllerAnimated(true, completion = null)
                continuation = null
            }
        }
    }

    override fun imagePickerController(
        picker: UIImagePickerController,
        didFinishPickingMediaWithInfo: Map<Any?, *>?
    ) {
        picker.dismissViewControllerAnimated(true, completion = null)

        val image = didFinishPickingMediaWithInfo?.get(UIImagePickerControllerOriginalImage) as? UIImage
        val imageData: NSData? = image?.JPEGRepresentation(1.0)

        if (imageData != null) {
            val result = ImageResult(
                name = "${NSUUID().UUIDString}.jpg",
                bytes = imageData.toByteArray()
            )
            continuation?.resume(result)
        } else {
            continuation?.resume(null)
        }
        continuation = null
    }

    override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
        picker.dismissViewControllerAnimated(true, completion = null)
        continuation?.resume(null)
        continuation = null
    }

    private fun UIImage.JPEGRepresentation(quality: Double): NSData? {
        return UIImageJPEGRepresentation(this, quality)
    }

    private fun NSData.toByteArray(): ByteArray {
        val buffer = this.bytes?.reinterpret<ByteVar>() ?: return ByteArray(0)
        return ByteArray(this.length.toInt()) { index -> buffer[index] }
    }
}
