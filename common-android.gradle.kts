val isLibraryPlugin = pluginManager.hasPlugin("com.android.library")
val isApplicationPlugin = pluginManager.hasPlugin("com.android.application")

if (!isLibraryPlugin || !isApplicationPlugin) return


android {
    defaultConfig {
        if (isApplicationPlugin) {
            applicationId = "com.keyme.app"
            versionCode = AppConfig.versionCode
            versionName = AppConfig.versionName
        }
        targetSdk = AppConfig.targetSdkVersion
        testInstrumentationRunner = AppConfig.androidTestInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}