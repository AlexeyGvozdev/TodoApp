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
    implementation(project(Config.Modules.core))

    implementation(Dependency.Navigation.fragment)
    implementation(Dependency.Navigation.ui)

    implementation(project(Config.Modules.coreDbApi))

    implementation(Dependency.Ui.material)
    implementation(Dependency.Ui.recyclerView)

    implementation(Dependency.DaggerDI.dagger)
    kapt(Dependency.DaggerDI.daggerCompiler)

    testImplementation(Dependency.Testing.junit)
    androidTestImplementation(Dependency.Testing.junitExt)
    androidTestImplementation(Dependency.Testing.espressoCore)
}