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

        maven("https://dl.buzzvil.com/public/maven") // 버즈빌 저장소
    }
}

rootProject.name = "buzzvil-sdk-v6-sample"
include(":app")
