import Dependencies.setAndroidTestDependencies
import Dependencies.setComposeDependencies
import Dependencies.setCoreKtxDependencies
import Dependencies.setCoroutinesDependencies
import Dependencies.setHiltDependencies
import Dependencies.setImageDependencies
import Dependencies.setKakaoSignInDependencies
import Dependencies.setKotlinStdLibDependencies
import Dependencies.setLoggerDependencies
import Dependencies.setLottieDependencies
import Dependencies.setTestDependencies
import Dependencies.setViewModelDependencies


plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("org.jlleitschuh.gradle.ktlint") version Versions.KTLINT
}

android {
    namespace = "com.keyme.presentation"
    compileSdk = AppConfig.compileSdkVersion

    @Suppress("UnstableApiUsage")
    defaultConfig {
        minSdk = AppConfig.minSdkVersion
    }

    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }

    composeOptions {
        // See https://developer.android.com/jetpack/androidx/releases/compose-kotlin
        kotlinCompilerExtensionVersion = "1.4.0"
    }

    // See https://github.com/Kotlin/kotlinx.coroutines#avoiding-including-the-debug-infrastructure-in-the-resulting-apk
    @Suppress("UnstableApiUsage")
    packagingOptions {
        resources.excludes += "DebugProbesKt.bin"
    }
}

dependencies {
    implementation(project(":domain"))

    setKotlinStdLibDependencies()
    setCoroutinesDependencies()
    setCoreKtxDependencies()

    setComposeDependencies()
    setViewModelDependencies()

    setKakaoSignInDependencies()
    setLottieDependencies()
    setImageDependencies()

    setHiltDependencies()

    setTestDependencies()
    setAndroidTestDependencies()

    setLoggerDependencies()
}

kapt {
    correctErrorTypes = true
}
