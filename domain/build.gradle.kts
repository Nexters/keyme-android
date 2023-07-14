import Dependencies.setJavaXInjectDependencies
import Dependencies.setKotlinStdLibDependencies
import Dependencies.setTestDependencies


plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    setKotlinStdLibDependencies()
    setJavaXInjectDependencies()
    setTestDependencies()
}