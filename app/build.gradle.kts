import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.dagger.hilt)
}

val properties = Properties().apply { load(project.rootProject.file("local.properties").inputStream()) }

android {
    namespace = "com.univoice"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.univoice"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "UNI_BASE_URL", properties["uni.base.url"].toString())
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
        compose = true
    }
}

dependencies {
    // AndroidX
    implementation(libs.bundles.androidx)

    // Navigation
    implementation(libs.bundles.navigation)

    // Google
    implementation(libs.material)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Network
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)
    implementation(platform(libs.okhttp3.bom))
    implementation(libs.kotlinx.serialization.json)

    // Third-Party
    implementation(libs.timber)
    implementation(libs.coil)
    implementation(libs.viewPager2)
    implementation(libs.glide)

    // Hilt
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)
    androidTestImplementation(libs.dagger.hilt.testing)
    kaptAndroidTest(libs.dagger.hilt.testing.compiler)

    // Compose
    implementation(libs.bundles.compose)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
    implementation(libs.coil.compose)
    implementation(libs.glide.compose)
}