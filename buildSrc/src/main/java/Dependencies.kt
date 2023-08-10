import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.Dependency


object Dependencies {

    // Kotlin
    private val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"

    // Coroutines
    private val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"

    // Core ktx
    private val coreKtx = "androidx.core:core-ktx:${Versions.KOTLIN}"

    // Splash
    private val splash = "androidx.core:core-splashscreen:${Versions.SPLASH}"

    // Activity
    private val activity = "androidx.activity:activity-ktx:${Versions.ACTIVITY}"
    private val activity_compose = "androidx.activity:activity-compose:${Versions.ACTIVITY}"

    // Compose
    // See https://developer.android.com/jetpack/compose/bom/bom-mapping
    private val composeBom = "androidx.compose:compose-bom:${Versions.COMPOSE_BOM}"
    private val composeMaterial = "androidx.compose.material3:material3"
    private val composePreview = "androidx.compose.ui:ui-tooling"
    private val composeLiveData = "androidx.compose.runtime:runtime-livedata"
    private val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.COMPOSE_VIEW_MODEL}"
    private val composeNavigation = "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION}"

    // ViewModel
    private val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"

    // Kakao signIn
    private val kakaoSignIn = "com.kakao.sdk:v2-user:${Versions.KAKAO_SIGN_IN}"

    // Lottie
    private val lottieCompose = "com.airbnb.android:lottie-compose:${Versions.LOTTIE_COMPOSE}"

    // Image
    private val glide = "com.github.bumptech.glide:glide:${Versions.GLIDE}"

    // Network
    private val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    private val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    private val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_INTERCEPTOR}"

    // Database
    private val room = "androidx.room:room-runtime:${Versions.ROOM}"
    private val roomKtx = "androidx.room:room-ktx:${Versions.ROOM}"
    private val roomCompiler = "androidx.room:room-compiler:${Versions.ROOM}"
    private val roomTesting = "androidx.room:room-testing:${Versions.ROOM}"

    // Dependency injection
    private val hilt = "com.google.dagger:hilt-android:${Versions.HILT}"
    private val hiltCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
    private val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
    private val javaXInject = "javax.inject:javax.inject:${Versions.JAVA_X_INJECT}"

    // Test
    private val junit = "junit:junit:${Versions.JUNIT}"

    // Android test
    private val androidJunit = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"

    fun DependencyHandler.setActivityDepdendencies() {
        implementation(activity)
        implementation(activity_compose)
    }

    fun DependencyHandler.setKotlinStdLibDependencies() {
        implementation(kotlinStdLib)
    }

    fun DependencyHandler.setCoroutinesDependencies() {
        implementation(coroutines)
    }

    fun DependencyHandler.setCoreKtxDependencies() {
        implementation(coreKtx)
    }

    fun DependencyHandler.setSplashDependencies() {
        implementation(splash)
    }

    fun DependencyHandler.setComposeDependencies() {
        implementation(platform(composeBom))
        implementation(composeMaterial)
        implementation(composePreview)
        implementation(composeLiveData)
        implementation(composeViewModel)
        implementation(composeNavigation)
    }

    fun DependencyHandler.setViewModelDependencies() {
        implementation(viewModelKtx)
    }

    fun DependencyHandler.setKakaoSignInDependencies() {
        implementation(kakaoSignIn)
    }

    fun DependencyHandler.setLottieDependencies() {
        implementation(lottieCompose)
    }

    fun DependencyHandler.setImageDependencies() {
        implementation(glide)
    }

    fun DependencyHandler.setNetworkDependencies() {
        implementation(retrofit)
        implementation(gsonConverter)
        implementation(okhttpInterceptor)
    }

    fun DependencyHandler.setDatabaseDependencies() {
        kapt(roomCompiler)
        annotationProcessor(roomCompiler)
        implementation(room)
        implementation(roomKtx)
        testImplementation(roomTesting)
    }

    fun DependencyHandler.setHiltDependencies() {
        kapt(hiltCompiler)
        implementation(hilt)
        implementation(hiltCompose)
    }

    fun DependencyHandler.setJavaXInjectDependencies() {
        implementation(javaXInject)
    }

    fun DependencyHandler.setTestDependencies() {
        testImplementation(junit)
    }

    fun DependencyHandler.setAndroidTestDependencies() {
        androidTestImplementation(androidJunit)
        androidTestImplementation(espressoCore)
    }


    private fun DependencyHandler.ksp(dependency: String) {
        add("ksp", dependency)
    }

    private fun DependencyHandler.kapt(dependency: String) {
        add("kapt", dependency)
    }

    private fun DependencyHandler.compileOnly(dependency: String) {
        add("compileOnly", dependency)
    }

    private fun DependencyHandler.implementation(dependency: String) {
        add("implementation", dependency)
    }

    private fun DependencyHandler.implementation(dependency: Dependency) {
        add("implementation", dependency)
    }

    private fun DependencyHandler.annotationProcessor(dependency: String) {
        add("annotationProcessor", dependency)
    }

    private fun DependencyHandler.testImplementation(dependency: String) {
        add("testImplementation", dependency)
    }

    private fun DependencyHandler.androidTestImplementation(dependency: String) {
        add("androidTestImplementation", dependency)
    }
}
