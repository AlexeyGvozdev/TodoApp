plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.androidKotlin)

    id(Config.Plugins.conventionAppConfig)
    id(Config.Plugins.conventionBuildTypes)
    id(Config.Plugins.conventionKotlin)
}

dependencies {

    implementation(Dependency.Android.core)
    implementation(Dependency.Compat.appCompat)

    implementation(Dependency.Ui.material)

    implementation(project(Config.Modules.core))
    api(project(Config.Modules.coreDbApi))
    implementation(project(mapOf("path" to ":coreDB")))

    testImplementation(Dependency.Testing.junit)
    androidTestImplementation(Dependency.Testing.junitExt)
    androidTestImplementation(Dependency.Testing.espressoCore)
}