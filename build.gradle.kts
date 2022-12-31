import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import java.util.Properties
import java.io.FileInputStream

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.7.10"
    id("org.pushing-pixels.aurora.tools.svgtranscoder.gradle") version "1.3.0"
}

group = "com.helium"
version = "1.0-SNAPSHOT"

repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Qawaz/compose-code-editor")
            credentials {
                val githubProperties = Properties()
                githubProperties.load(FileInputStream("github.properties"))
                username = (githubProperties["gpr.usr"] ?: System.getenv("GPR_USER")).toString()
                password = (githubProperties["gpr.key"] ?: System.getenv("GPR_API_KEY")).toString()
            }
        }
}
buildscript {
    dependencies {
        classpath("org.pushing-pixels:aurora-tools-svg-transcoder-gradle-plugin:1.3.0")
    }
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val commonMain by getting {

        }
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("com.wakaztahir:codeeditor:3.0.5")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.compose.material3:material3-desktop:1.2.2")

                sourceSets["jvmMain"].apply {
                    kotlin.srcDir("$rootDir/src/jvmMain/kotlin")
                    kotlin.srcDir("$rootDir/src/gen/kotlin")
                }
            }
        }
        val jvmTest by getting
    }

}
tasks.register<org.pushingpixels.aurora.tools.svgtranscoder.gradle.TranscodeDeepTask>("transcodeFolder") {
    inputRootDirectory = file("src/jvmMain/resources/scalable")
    outputRootDirectory = file("src/gen/kotlin/com/helium/scalable/svg")
    outputRootPackageName = "com.helium.scalable.svg"
    transcode()
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