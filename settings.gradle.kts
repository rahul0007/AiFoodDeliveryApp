rootProject.name = "AiFoodDeliveryApp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        // ✅ Required for SQLDelight 2.x
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/sqldelight/maven")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        // ✅ Required
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/sqldelight/maven")
    }
}

include(":composeApp")

