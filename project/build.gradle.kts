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
    implementation(project(Config.Modules.core))

    implementation(Dependency.Ui.material)
    implementation(Dependency.Ui.recyclerView)

    testImplementation(Dependency.Testing.junit)
    androidTestImplementation(Dependency.Testing.junitExt)
    androidTestImplementation(Dependency.Testing.espressoCore)
}