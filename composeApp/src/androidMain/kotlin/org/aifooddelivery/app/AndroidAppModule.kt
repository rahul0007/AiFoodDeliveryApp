package org.aifooddelivery.app
import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import org.aifooddelivery.app.core.database.DatabaseDriverFactory
import org.aifooddelivery.app.utils.DataStoreManager
import org.aifooddelivery.app.utils.ImagePicker
import org.koin.dsl.module


val androidAppModule = module {
    single<DataStoreManager> { AndroidDataStoreManager(get()) }  // get() provides Android Context


    single { DatabaseDriverFactory(get()) }


}

fun platformModule(context: Context) = module {
    single { DatabaseDriverFactory(context) } // Factory itself
    single<SqlDriver> { get<DatabaseDriverFactory>().createDriver() } // Provide SqlDriver
}

val platformModule = module {
    factory { (launchGallery: () -> Unit,
                  launchCamera: () -> Unit,
                  requestPermission: (String) -> Unit,
                  checkPermission: (String) -> Boolean) ->
        AndroidImagePicker(
            launchGallery,
            launchCamera,
            requestPermission,
            checkPermission
        ) as ImagePicker
    }
}