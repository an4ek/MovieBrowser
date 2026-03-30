pluginManagement {
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

rootProject.name = "BookBrowser"

include(":app")
include(":network")
include(":feature-premium")
include(":feature-non-premium")

// Задание 6 - buildCache
buildCache {
    local {
        isEnabled = true
        directory = File(rootDir, ".gradle/build-cache")
    }
}
include(":network")
include(":feature-premium")
include(":feature-non-premium")
