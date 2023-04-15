rootProject.name = "vhr-remake"

pluginManagement {
    val kotlin: String by settings
    val springboot: String by settings
    repositories {
        gradlePluginPortal()
    }

    plugins {
        id("org.springframework.boot") version springboot
        kotlin("jvm") version kotlin
        kotlin("plugin.spring") version kotlin
        kotlin("kapt") version kotlin
        id("com.google.devtools.ksp") version "${kotlin}-1.0.9"
    }
}
