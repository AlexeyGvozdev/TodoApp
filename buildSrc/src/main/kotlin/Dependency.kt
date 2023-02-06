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

    object Ui {
        private const val materialVersion = "1.7.0"
        private const val constraintLayoutVersion = "2.1.4"
        private const val recyclerViewVersion = "1.2.1"

        const val material = "com.google.android.material:material:$materialVersion"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        const val recyclerView = "androidx.recyclerview:recyclerview:$recyclerViewVersion"
    }

    object Room {
        private const val roomVersion = "2.4.3"

        const val runtime = "androidx.room:room-runtime:$roomVersion"
        const val compiler = "androidx.room:room-compiler:$roomVersion"
        const val ktx = "androidx.room:room-ktx:$roomVersion"
    }

    object Testing {
        private const val junitVersion = "4.13.2"
        private const val junitExtVersion = "1.1.5"
        private const val espressoCoreVersion = "3.5.1"

        const val junit = "junit:junit:$junitVersion"
        const val junitExt = "androidx.test.ext:junit:$junitExtVersion"
        const val espressoCore = "androidx.test.espresso:espresso-core:$espressoCoreVersion"
    }
}