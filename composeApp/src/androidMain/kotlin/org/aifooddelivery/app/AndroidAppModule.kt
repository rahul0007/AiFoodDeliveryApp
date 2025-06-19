package org.aifooddelivery.app

import android.content.Context
import androidx.activity.ComponentActivity
import app.cash.sqldelight.db.SqlDriver
import org.aifooddelivery.DatabaseDriverFactory
import org.aifooddelivery.app.utils.DataStoreManager
import org.aifooddelivery.app.utils.ImagePicker
import org.koin.dsl.module


val androidAppModule = module {
    single<DataStoreManager> { AndroidDataStoreManager(get()) }  // get() provides Android Context

    factory { (activity: ComponentActivity) -> AndroidImagePicker(activity) as ImagePicker }
    single { DatabaseDriverFactory(get()) }


}

fun platformModule(context: Context) = module {
    single { DatabaseDriverFactory(context) } // Factory itself
    single<SqlDriver> { get<DatabaseDriverFactory>().createDriver() } // Provide SqlDriver
}