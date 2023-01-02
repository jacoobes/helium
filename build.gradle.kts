import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.7.10"
}

group = "com.helium"
version = "1.0-SNAPSHOT"

repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://jitpack.io")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                val lwjglVersion = "3.3.1"
                implementation(compose.desktop.currentOs)
                // https://mvnrepository.com/artifact/org.jetbrains.compose.material3/material3
                implementation("org.jetbrains.compose.material3:material3:1.2.2")

                implementation("com.github.Qawaz.compose-code-editor:codeeditor-desktop:3.0.5")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                listOf("lwjgl", "lwjgl-nfd").forEach { lwjglDep ->
                    implementation("org.lwjgl:${lwjglDep}:${lwjglVersion}")
                    listOf(
                        "natives-windows", "natives-windows-x86", "natives-windows-arm64",
                        "natives-macos", "natives-macos-arm64",
                        "natives-linux", "natives-linux-arm64", "natives-linux-arm32"
                    ).forEach { native ->
                        runtimeOnly("org.lwjgl:${lwjglDep}:${lwjglVersion}:${native}")
                    }
                }
                sourceSets["jvmMain"].apply {
                    kotlin.srcDir("$rootDir/src/jvmMain/kotlin")
                    kotlin.srcDir("$rootDir/src/gen/kotlin")
                }
            }
        }
        val jvmTest by getting
    }

}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "helium"
            packageVersion = "1.0.0"
        }
    }
}