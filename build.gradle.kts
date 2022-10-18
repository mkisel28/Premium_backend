val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val Exposed_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.20"
    id("io.ktor.plugin") version "2.1.2"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
}

group = "news.slivy"
version = "0.0.1"
application {
    mainClass.set("news.slivy.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
/// maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-default-headers-jvm:$ktor_version")

    implementation("io.ktor:ktor-server-cio-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")

    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    implementation("org.jetbrains.exposed:exposed-core:$Exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$Exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$Exposed_version")

    implementation("org.postgresql:postgresql:42.3.1")


    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}


//ktor {
//    fatJar {
//        archiveFileName.set("fat.jar")
//    }
//}