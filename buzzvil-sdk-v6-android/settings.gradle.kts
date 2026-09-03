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
        maven("https://devrepo.kakao.com/nexus/content/groups/public/") // kakao adfit (buzz-banner adcash 미디에이션)
        maven("https://cauly.github.io/cauly-sdk-android-maven/maven-repo") // cauly (buzz-banner adcash 미디에이션)
    }
}

rootProject.name = "buzzvil-sdk-v6-sample"
include(":app")
