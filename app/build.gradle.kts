import Dependencies.setActivityDepdendencies
import Dependencies.setAndroidTestDependencies
import Dependencies.setComposeDependencies
import Dependencies.setCoreKtxDependencies
import Dependencies.setHiltDependencies
import Dependencies.setKotlinStdLibDependencies
import Dependencies.setSplashDependencies
import Dependencies.setTestDependencies


@Suppress("UnstableApiUsage")
plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("org.jlleitschuh.gradle.ktlint") version Versions.KTLINT
}

android {
    namespace = "com.keyme.app"
    compileSdk = AppConfig.compileSdkVersion

    @Suppress("UnstableApiUsage")
    defaultConfig {
        minSdk = AppConfig.minSdkVersion
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        // See https://developer.android.com/jetpack/androidx/releases/compose-kotlin
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    setKotlinStdLibDependencies()
    setCoreKtxDependencies()

    setSplashDependencies()
    setActivityDepdendencies()
    setComposeDependencies()

    setHiltDependencies()

    setTestDependencies()
    setAndroidTestDependencies()
}

kapt {
    correctErrorTypes = true
}
