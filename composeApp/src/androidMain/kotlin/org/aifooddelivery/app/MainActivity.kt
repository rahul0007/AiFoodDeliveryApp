package org.aifooddelivery.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import org.aifooddelivery.app.core.di.appModule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
// Make status bar transparent

// Optional: control status bar icon colors (true = dark icons for light backgrounds)
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            App()
            val context = LocalContext.current
            // Set context only once
            LaunchedEffect(Unit) {
                initToastContext(context)
            }

        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()

}