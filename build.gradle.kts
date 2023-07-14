// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.google.dagger.hilt.android") version Versions.HILT apply false
    id("org.jetbrains.kotlin.android") version Versions.KOTLIN apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.ANDROID_GRADLE}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
    }
}

allprojects {
    afterEvaluate {
        project.apply { "$rootDir/common-android.gradle.kts" }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}