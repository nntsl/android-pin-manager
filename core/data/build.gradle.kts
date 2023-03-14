plugins {
    id("pinmanager.android.library")
    id("pinmanager.android.hilt")
}

android {
    namespace = "com.nntsl.pinmanager.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))

    testImplementation(project(":core:testing"))

    implementation(libs.kotlinx.coroutines.android)
}
