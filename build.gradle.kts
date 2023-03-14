buildscript {
    repositories {
        google()
        mavenCentral()
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    id("com.android.library") version "7.4.1" apply false
}