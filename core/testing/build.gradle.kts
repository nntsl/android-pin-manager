plugins {
    id("pinmanager.android.library")
    id("pinmanager.android.hilt")
}

android {
    namespace = "com.nntsl.pinmanager.core.testing"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(libs.junit4)
    api(libs.androidx.test.core)
    api(libs.kotlinx.coroutines.test)

    api(libs.androidx.test.runner)
    api(libs.androidx.test.rules)
    api(libs.androidx.compose.ui.test)
    api(libs.hilt.android.testing)
}
