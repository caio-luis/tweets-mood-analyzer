/**
 * Created by Caio Luis (caio-luis) on 29/03/21
 */

object CoreDependencies {
    const val ktxCore = "androidx.core:core-ktx:${KotlinCoreVersions.ktxCoreVersion}"
    const val kotlinStdLib =
        "org.jetbrains.kotlin:kotlin-stdlib:${KotlinCoreVersions.kotlinVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${KotlinCoreVersions.appCompatVersion}"
    const val gradleAndroid = "com.android.tools.build:gradle:${BuildVersions.androidGradle}"
    const val gradleKotlin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${KotlinCoreVersions.kotlinVersion}"
}

object TestDependencies {
    const val jUnit = "androidx.test.ext:junit:${TestVersions.androidxJunit}"
    const val assertJ = "org.assertj:assertj-core:${TestVersions.assertJ}"
    const val androidTestCore = "androidx.test:core:${TestVersions.androidTestArchCore}"
    const val androidTestRunner = "androidx.test:runner:${TestVersions.androidTextRunner}"
    const val androidTestRules = "androidx.test:rules:${TestVersions.androidTextRules}"
    const val coreTesting = "androidx.arch.core:core-testing:${TestVersions.coreTesting}"
    const val  mockk = "io.mockk:mockk:${TestVersions.mockk}"
    const val  coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${TestVersions.coroutinesTest}"
}

object Dependencies {
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${DesignVersions.constraintLayoutVersion}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val okhttpLogger =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptor}"

    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val koin = "org.koin:koin-core:${Versions.koin}"
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    const val lifeCycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifeCycleKapt = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifeCycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifeCycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
}
