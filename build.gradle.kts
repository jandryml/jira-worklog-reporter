plugins {
    kotlin("jvm") version "1.9.22"
}

group = "cz.jdr.app"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktorVersion = "3.0.0"

dependencies {
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