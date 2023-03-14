pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "PIN manager"
include(":app")
include(":core")
include(":core:data")
include(":core:database")
include(":core:testing")
include(":feature:pin")
include(":core:domain")
include(":core:model")
