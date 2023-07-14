import Dependencies.setAndroidTestDependencies
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
}

android {
    namespace = "com.keyme.app"
    compileSdk = AppConfig.compileSdkVersion

    @Suppress("UnstableApiUsage")
    defaultConfig {
        minSdk = AppConfig.minSdkVersion
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    setKotlinStdLibDependencies()
    setCoreKtxDependencies()

    setSplashDependencies()

    setHiltDependencies()

    setTestDependencies()
    setAndroidTestDependencies()
}

kapt {
    correctErrorTypes = true
}