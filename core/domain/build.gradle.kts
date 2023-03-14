plugins {
    id("pinmanager.android.library")
    id("pinmanager.android.hilt")
}

android {
    namespace = "com.nntsl.pinmanager.core.domain"

}

dependencies {
    implementation(project(":core:model"))

    testImplementation(project(":core:testing"))
    testImplementation(kotlin("test"))
}
