import Dependencies.setAndroidTestDependencies
import Dependencies.setCoreKtxDependencies
import Dependencies.setCoroutinesDependencies
import Dependencies.setDatabaseDependencies
import Dependencies.setHiltDependencies
import Dependencies.setKotlinStdLibDependencies
import Dependencies.setLoggerDependencies
import Dependencies.setNetworkDependencies
import Dependencies.setTestDependencies


plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("org.jlleitschuh.gradle.ktlint") version Versions.KTLINT
}

android {
    namespace = "com.keyme.data"
    compileSdk = AppConfig.compileSdkVersion

    @Suppress("UnstableApiUsage")
    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        consumerProguardFiles(AppConfig.proguardConsumerRules)
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

    setNetworkDependencies()
    setDatabaseDependencies()

    setHiltDependencies()

    setTestDependencies()
    setAndroidTestDependencies()

    setLoggerDependencies()
}

kapt {
    correctErrorTypes = true
}
