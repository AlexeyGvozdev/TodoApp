plugins {
    id(Config.Plugins.androidApplication)
    id(Config.Plugins.androidKotlin)
    id(Config.Plugins.kotlinKapt)

    id(Config.Plugins.conventionAppConfig)
    id(Config.Plugins.conventionBuildTypes)
    id(Config.Plugins.conventionKotlin)
}

android {
    defaultConfig {
        applicationId = "com.sinx.todo"
        versionCode = BuildVersions.versionCode
        versionName = BuildVersions.versionName
    }
}
dependencies {
    implementation(Dependency.Android.core)
    implementation(Dependency.Compat.appCompat)
    implementation(project(Config.Modules.core))

    implementation(Dependency.Navigation.fragment)
    implementation(Dependency.Navigation.ui)

    implementation(Dependency.Ui.material)
    implementation(Dependency.Ui.constraintLayout)

    implementation(project(Config.Modules.core))
    implementation(project(Config.Modules.project))
    implementation(project(Config.Modules.task))
    implementation(project(Config.Modules.coreDB))
    implementation(project(mapOf("path" to ":coreDB:coreDbApi")))

    testImplementation(Dependency.Testing.junit)
    androidTestImplementation(Dependency.Testing.junitExt)
    androidTestImplementation(Dependency.Testing.espressoCore)

    implementation(Dependency.Dagger.dagger)
    kapt(Dependency.Dagger.daggerCompiler)
}