rootProject.name = "vhr-remake"

pluginManagement {
    val kotlin: String by settings
    repositories {
        gradlePluginPortal()
    }

    plugins {
        kotlin("jvm") version kotlin
        kotlin("plugin.spring") version kotlin
        kotlin("kapt") version kotlin
        id("com.google.devtools.ksp") version "${kotlin}-1.0.9"
    }
}
