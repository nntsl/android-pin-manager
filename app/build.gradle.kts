import com.nntsl.pinmanager.PinManagerBuildType

plugins {
    id("pinmanager.android.application")
    id("pinmanager.android.hilt")
    id("pinmanager.android.application.compose")
}

android {
    namespace = "com.nntsl.pinmanager"

    defaultConfig {
        applicationId = "com.nntsl.pinmanager"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val debug by getting {
            isDebuggable = true
            applicationIdSuffix = PinManagerBuildType.DEBUG.applicationIdSuffix
        }
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.appcompat)
}