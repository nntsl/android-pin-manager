plugins {
    id("pinmanager.android.feature")
    id("pinmanager.android.hilt")
    id("pinmanager.android.library.compose")
}

android {
    namespace = "com.nntsl.pinmanager.feature.pin"
}

dependencies {
    implementation(libs.androidx.compose.material3)
}