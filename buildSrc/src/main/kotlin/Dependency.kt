object Dependency {

    object Navigation {
        private const val navigationVersion = "2.5.3"

        const val fragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val ui = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
    }

    object Android {
        private const val coreVersion = "1.9.0"
        const val core = "androidx.core:core-ktx:$coreVersion"
    }

    object Compat {
        private const val compatVersion = "1.6.0"
        const val appCompat = "androidx.appcompat:appcompat:$compatVersion"
    }

    object Room {
        private const val room_version = "2.5.0"

        const val runtime = "androidx.room:room-runtime:$room_version"
        const val compiler = "androidx.room:room-compiler:$room_version"
        const val ktx = "androidx.room:room-ktx:$room_version"
    }

    object Dagger {
        private const val daggerVersion = "2.45"

        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val compiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    }

    object Ui {
        private const val materialVersion = "1.7.0"
        private const val constraintLayoutVersion = "2.1.4"
        private const val recyclerViewVersion = "1.2.1"

        const val material = "com.google.android.material:material:$materialVersion"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        const val recyclerView = "androidx.recyclerview:recyclerview:$recyclerViewVersion"
    }

    object Testing {
        private const val junitVersion = "4.13.2"
        private const val junitExtVersion = "1.1.5"
        private const val espressoCoreVersion = "3.5.1"
        private const val coroutines_version = "1.6.4"

        const val junit = "junit:junit:$junitVersion"
        const val junitExt = "androidx.test.ext:junit:$junitExtVersion"
        const val espressoCore = "androidx.test.espresso:espresso-core:$espressoCoreVersion"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version"
    }

    object Dagger {
        private const val daggerVersion = "2.46"

        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    }
}