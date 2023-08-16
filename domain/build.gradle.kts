import Dependencies.setCoroutinesDependencies
import Dependencies.setJavaXInjectDependencies
import Dependencies.setKotlinStdLibDependencies
import Dependencies.setLoggerDependencies
import Dependencies.setPagingDependenciesForDomain
import Dependencies.setTestDependencies


plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("org.jlleitschuh.gradle.ktlint") version Versions.KTLINT
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    setKotlinStdLibDependencies()
    setJavaXInjectDependencies()
    setCoroutinesDependencies()

    setTestDependencies()
    setLoggerDependencies()
    setPagingDependenciesForDomain()
}
