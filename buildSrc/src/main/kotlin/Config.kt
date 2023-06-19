object Config {
    object Modules {
        const val core = ":core"
        const val project = ":project"
        const val task = ":task"
        const val taskList = ":taskList"
        const val coreDB = ":coreDB"
        const val coreDbApi = ":coreDB:coreDbApi"
        const val coreDbImpl = ":coreDB:coreDbImpl"
    }

    object Plugins {
        object Versions {
            const val gradleVersion = "7.3.1"
            const val kotlinAndroidVersion = "1.7.20"
            const val detektVersion = "1.23.0"
        }

        const val androidApplication = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val androidKotlin = "org.jetbrains.kotlin.android"
        const val detektPlugin = "io.gitlab.arturbosch.detekt"
        const val kotlinKapt = "kotlin-kapt"

        /**
         * Про самописные плагины:
         * 1. [https://docs.gradle.org/current/userguide/custom_plugins.html#sec:precompiled_plugins]
         * 2. [https://www.youtube.com/watch?v=8tsSSIOFxZg&ab_channel=red_mad_robotAndroid]
         */
        const val conventionAppConfig = "app-config-convention"
        const val conventionBuildTypes = "build-types-convention"
        const val conventionKotlin = "android-kotlin-convention"
    }
}