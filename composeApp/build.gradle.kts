import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)

    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.sqldelight)


}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    val iosX64 = iosX64()
    val iosArm64 = iosArm64()
    val iosSimulatorArm64 = iosSimulatorArm64()

    val iosMain by sourceSets.creating {
        dependsOn(sourceSets["commonMain"])
    }
    val iosX64Main = sourceSets["iosX64Main"]
    val iosArm64Main = sourceSets["iosArm64Main"]
    val iosSimulatorArm64Main = sourceSets["iosSimulatorArm64Main"]

    iosX64Main.dependsOn(iosMain)
    iosArm64Main.dependsOn(iosMain)
    iosSimulatorArm64Main.dependsOn(iosMain)
    jvm("desktop")
    sourceSets {
        getByName("androidMain").dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.activity)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.ktor.client.android)
            implementation(libs.system.ui.controller)
            implementation(libs.compose.material.icons.extended)
            implementation(libs.compose.material3)
            implementation(libs.jakewharton.threetenabp)
            implementation(libs.androidx.data.store.core)
            implementation(libs.androidx.data.store.preferences)
            implementation(libs.sqldelight.android)
            implementation(libs.koin.core)

        }

        getByName("commonMain").dependencies {
            implementation(libs.sqldelight.coroutines)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(libs.system.ui.controller)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.koin.core)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screen.model)
            implementation(libs.voyager.transitions)
            implementation(libs.voyager.koin)
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.log)
            implementation(libs.kotlinx.datetime)
            implementation(libs.multiplatform.settings.no.arg)
            implementation(libs.multiplatform.settings.coroutines)
            implementation(libs.mongodb.realm)
            implementation(libs.kotlin.coroutines)
            implementation(libs.stately.common)
            implementation(libs.navigation.compose)
            implementation(libs.androidx.data.store.core)
            implementation(libs.androidx.data.store.preferences)
            implementation(libs.screen.size)
            api(libs.koin.cored)
            implementation(libs.koin.compose)
            implementation(libs.koin.composeVM)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.foundation.pager)
            implementation(libs.compose.runtime)
        }



        getByName("iosMain").dependencies {
            implementation(libs.sqldelight.native)
            implementation(libs.ktor.client.darwin)

        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutinesSwing)
            }
        }
       /* getByName("desktopMain").dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)

        }*/


    }
    sqldelight {
        databases {
            create("MyDataBase") {
                packageName = "com.aifood"
            }
        }
    }

}

android {
    namespace = "org.aifooddelivery.app"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.aifooddelivery.app"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}
compose.desktop {
    application {
        mainClass = "org.aifooddelivery.app.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.aifooddelivery.app"
            packageVersion = "1.0.0"
        }
    }
}

