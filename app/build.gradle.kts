plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.yuriy.diapason"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.yuriy.diapason"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Core Compose
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.ui.graphics)
    debugImplementation(libs.androidx.ui.tooling)

    // Material 3 with dynamic color / Material You
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.material3)
    implementation(libs.material.icons.extended)
    implementation(libs.material)

    // Navigation Compose
    implementation(libs.androidx.navigation.compose)

    // ViewModel + Lifecycle in Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Activity Compose (setContent, rememberLauncherForActivityResult)
    implementation(libs.androidx.activity.compose)

    // Accompanist Permissions — clean Compose API for runtime permissions
    implementation(libs.accompanist.permissions)

    // Core KTX
    implementation(libs.androidx.core.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
}
