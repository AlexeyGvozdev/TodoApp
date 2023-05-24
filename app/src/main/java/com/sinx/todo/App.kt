package com.sinx.todo

import android.app.Application
import com.sinx.task.presentation.TaskDepsStore

class App : Application() {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(context = this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        TaskDepsStore.deps = appComponent
    }
}
