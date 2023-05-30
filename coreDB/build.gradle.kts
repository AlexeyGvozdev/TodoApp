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
    implementation(project(Config.Modules.coreDbApi))

    implementation(Dependency.Room.runtime)
    kapt(Dependency.Room.compiler)
    implementation(Dependency.Room.ktx)

    implementation(Dependency.DaggerDI.dagger)
    kapt(Dependency.DaggerDI.daggerCompiler)
}