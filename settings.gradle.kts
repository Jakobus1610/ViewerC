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
        //maven { url("http://jcenter.bintray.com") }
        maven { setUrl("https://jitpack.io") }
        //maven { setUrl("http://jcenter.bintray.com") }
        gradlePluginPortal()
    }
}

rootProject.name = "Viewer C++"
include(":app")
 