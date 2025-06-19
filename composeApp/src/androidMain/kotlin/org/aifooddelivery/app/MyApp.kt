package org.aifooddelivery.app

import android.app.Application
import org.aifooddelivery.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp) // ✅ Required to inject context
            modules(
                listOf(
                    appModule,        // Common module (no DataStoreManager here!)
                    androidAppModule, platformModule(this@MyApp)  // Android-specific binding
                )
            )
        }
    }
}