plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.androidKotlin)
    id(Config.Plugins.kotlinKapt)

    id(Config.Plugins.conventionAppConfig)
    id(Config.Plugins.conventionBuildTypes)
    id(Config.Plugins.conventionKotlin)
}

dependencies {
    implementation(Dependency.Android.core)
    implementation(Dependency.Compat.appCompat)

    implementation(project(Config.Modules.taskList))
    implementation(project(Config.Modules.core))
    implementation(project(Config.Modules.coreDbApi))

    implementation(Dependency.Navigation.fragment)
    implementation(Dependency.Navigation.ui)

    implementation(Dependency.Ui.material)

    testImplementation(Dependency.Testing.junit)
    androidTestImplementation(Dependency.Testing.junitExt)
    androidTestImplementation(Dependency.Testing.espressoCore)
    testImplementation(Dependency.Testing.coroutinesTest)

    implementation(Dependency.Dagger.dagger)
    kapt(Dependency.Dagger.compiler)
}