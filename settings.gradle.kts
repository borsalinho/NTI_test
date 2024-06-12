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

rootProject.name = "testnti"
include(":appclient")
include(":appserver")
include(":domain")
include(":dataclient")
include(":dataserver")
include(":localserveronmac")
