import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("kapt")
    id("com.google.devtools.ksp")
}

group = "cn.enaium"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

val jimmer: String by project
val mapstruct: String by project
val hutool: String by project
val satoken: String by project
val poi: String by project
val springdoc: String by project
val therapi: String by project

dependencies {
    implementation("org.babyfish.jimmer:jimmer-spring-boot-starter:$jimmer")
    ksp("org.babyfish.jimmer:jimmer-ksp:$jimmer")
    implementation("org.mapstruct:mapstruct:$mapstruct")
    kapt("org.mapstruct:mapstruct-processor:$mapstruct")
    kapt("org.babyfish.jimmer:jimmer-mapstruct-apt:$jimmer")

    implementation("cn.dev33:sa-token-spring-boot3-starter:$satoken")
    implementation("cn.dev33:sa-token-dao-redis-jackson:$satoken")
    implementation("org.apache.commons:commons-pool2")

    implementation("org.apache.poi:poi:$poi")

    implementation("cn.hutool:hutool-captcha:$hutool")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdoc")
    implementation("com.github.therapi:therapi-runtime-javadoc:$therapi")
    implementation("com.github.therapi:therapi-runtime-javadoc-scribe:$therapi")

    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.amqp:spring-rabbit-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}