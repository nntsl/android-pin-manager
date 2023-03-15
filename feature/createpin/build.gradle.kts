plugins {
    id("pinmanager.android.feature")
    id("pinmanager.android.hilt")
    id("pinmanager.android.library.compose")
}

android {
    namespace = "com.nntsl.pinmanager.feature.createpin"
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material)
}
