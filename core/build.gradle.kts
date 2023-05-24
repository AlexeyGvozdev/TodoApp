
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
    implementation(Dependency.Ui.material)
    implementation(Dependency.Ui.constraintLayout)

    implementation(Dependency.DaggerDI.dagger)
    kapt(Dependency.DaggerDI.daggerCompiler)
}