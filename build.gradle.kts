// Top-level build file where you can add configuration options common to all sub-projects/modules.
import io.gitlab.arturbosch.detekt.Detekt

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Config.Plugins.Versions.gradleVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Config.Plugins.Versions.kotlinAndroidVersion}")
    }
}

plugins {
    id(Config.Plugins.androidApplication) apply false
    id(Config.Plugins.androidLibrary) apply false
    id(Config.Plugins.androidKotlin) apply false
    id(Config.Plugins.detektPlugin).version(Config.Plugins.Versions.detektVersion)

}

tasks.register<Detekt>("detektAll") {
    description = "Runs over whole code base without the starting overhead for each module."
    parallel = true
    setSource(files(projectDir))
    buildUponDefaultConfig = false
    config.setFrom(files(project.rootDir.resolve("config/detekt/detekt.yml")))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
    reports {
        xml.required.set(false)
        html.required.set(false)
        txt.required.set(true)
    }
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")
}