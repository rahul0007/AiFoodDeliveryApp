package org.aifooddelivery.app

import android.app.Application
import org.aifooddelivery.app.core.database.di.databaseModule
import org.aifooddelivery.app.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    appModule,
                    databaseModule,
                    androidAppModule,
                    platformModule,platformModule(this@MyApp)
                )
            )
        }
    }
}