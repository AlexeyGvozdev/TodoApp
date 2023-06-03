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
    implementation(project(Config.Modules.taskList))

    testImplementation(Dependency.Testing.junit)
    androidTestImplementation(Dependency.Testing.junitExt)
    androidTestImplementation(Dependency.Testing.espressoCore)
}