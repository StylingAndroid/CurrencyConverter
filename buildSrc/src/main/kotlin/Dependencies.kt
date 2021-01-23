const val kotlinVersion = "1.4.30-RC"
const val hiltVersion = "2.31.2-alpha"
const val wireVersion = "3.5.0"

object BuildPlugins {
    object Version {
        const val androidBuildToolsVersion = "7.0.0-alpha04"
        const val versionsVersion = "0.33.0"
        const val detektVersion = "1.14.1"
        const val ktlintVersion = "9.4.0"
        const val canidropjetifierVersion = "0.5"
    }

    const val versions = "com.github.ben-manes.versions"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val ktlint = "org.jlleitschuh.gradle.ktlint"
    const val canidropjetifier = "com.github.plnice.canidropjetifier"

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Version.androidBuildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val hiltAbdroidPlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
    const val wirePlugin = "com.squareup.wire:wire-gradle-plugin:$wireVersion"

    const val versionsPlugin = "com.github.ben-manes:gradle-versions-plugin:${Version.versionsVersion}"
    const val detektPlugin = "$detekt:detekt-gradle-plugin:${Version.detektVersion}"
    const val ktlintPlugin = "org.jlleitschuh.gradle:ktlint-gradle:${Version.ktlintVersion}"
    const val canidropjetifierPlugin = "com.github.plnice:canidropjetifier:${Version.canidropjetifierVersion}"
}

object AndroidSdk {
    const val min = 21
    const val compile = 30
    const val target = compile
}


object Libraries {
    private object Versions {
        const val appCompat = "1.3.0-beta01"
        const val ktx = "1.5.0-beta01"
        const val ktxActivity = "1.2.0-rc01"
        const val ktxFragment = "1.3.0-rc01"
        const val desugaring = "1.0.10"
        const val constraintLayout = "2.1.0-alpha1"
        const val dataStore = "1.0.0-alpha06"
        const val material = "1.3.0-rc01"
        const val lifecycle = "2.3.0-rc01"
        const val navigation = "2.3.2"
        const val work = "2.5.0-rc01"
        const val hilt = "1.0.0-alpha02"
        const val room = "2.3.0-alpha04"

        const val retrofit = "2.9.0"
        const val okhttp3 = "4.10.0-RC1"
        const val moshi = "1.11.0"
        const val timber = "4.7.1"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val ktxActivity = "androidx.activity:activity-ktx:${Versions.ktxActivity}"
    const val ktxFragment = "androidx.fragment:fragment-ktx:${Versions.ktxFragment}"
    const val coreLibraryDesugaring = "com.android.tools:desugar_jdk_libs:${Versions.desugaring}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val dataStore = "androidx.datastore:datastore:${Versions.dataStore}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    const val hiltWork = "androidx.hilt:hilt-work:${Versions.hilt}"
    const val hiltLifecycleViewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hilt}"
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}"
    const val lifecycleService = "androidx.lifecycle:lifecycle-service:${Versions.lifecycle}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val workRuntime = "androidx.work:work-runtime:${Versions.work}"
    const val workRuntimeKtx = "androidx.work:work-runtime-ktx:${Versions.work}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val wireRuntime = "com.squareup.wire:wire-runtime:$wireVersion"
}

object TestLibraries {
    private object Versions {
        const val junit = "4.13.1"
    }

    const val junit = "junit:junit:${Versions.junit}"
}