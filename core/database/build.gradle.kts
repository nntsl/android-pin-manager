plugins {
    id("pinmanager.android.library")
    id("pinmanager.android.hilt")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.nntsl.pinmanager.core.database"
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(libs.kotlinx.coroutines.android)

    androidTestImplementation(project(":core:testing"))
}
