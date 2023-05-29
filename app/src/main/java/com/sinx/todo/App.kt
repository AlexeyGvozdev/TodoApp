package com.sinx.todo

import android.app.Application
import com.sinx.core.di.ComponentProvider
import com.sinx.task.di.TaskDeps
//import com.sinx.task.di.TaskDepsStore

class App : Application(), ComponentProvider<TaskDeps> {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(context = this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent
    }

    override val component: TaskDeps
        get() = appComponent
}
