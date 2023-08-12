import Dependencies.setActivityDepdendencies
import Dependencies.setAndroidTestDependencies
import Dependencies.setComposeDependencies
import Dependencies.setCoreKtxDependencies
import Dependencies.setHiltDependencies
import Dependencies.setKakaoSignInDependencies
import Dependencies.setKotlinStdLibDependencies
import Dependencies.setLoggerDependencies
import Dependencies.setSplashDependencies
import Dependencies.setTestDependencies
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties


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
        applicationId = "com.keyme.app"
        minSdk = AppConfig.minSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        manifestPlaceholders["KAKAO_NATIVE_APP_KEY"] = getKakaoNativeAppKey()
        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", getKakaoNativeAppKey())
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
    setKakaoSignInDependencies()

    setHiltDependencies()

    setTestDependencies()
    setAndroidTestDependencies()

    setLoggerDependencies()
}

kapt {
    correctErrorTypes = true
}


fun getKakaoNativeAppKey(): String {
    return gradleLocalProperties(rootDir).getProperty("kakao_native_app_key")
}
