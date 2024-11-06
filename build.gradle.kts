plugins {
    kotlin("jvm") version "1.9.22"
}

group = "cz.jdr.app"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val logbackVersion: String by project
val ktorVersion: String by project

dependencies {
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    //  For JVM
    implementation("io.ktor:ktor-client-cio-jvm:$ktorVersion")
    
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}