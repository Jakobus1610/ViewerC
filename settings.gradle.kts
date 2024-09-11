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
        //maven { url("http://jcenter.bintray.com") }
        maven { setUrl("https://jitpack.io") }
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "Viewer C++"
include(":app")
 