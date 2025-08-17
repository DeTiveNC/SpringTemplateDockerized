import org.gradle.api.initialization.resolve.RepositoriesMode

rootProject.name = "KSpringDoker"

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}
