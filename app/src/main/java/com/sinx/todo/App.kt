package com.sinx.todo

import android.app.Application
import com.sinx.coredbinterface.DbProvider
import com.sinx.coredbinterface.dao.TaskDAO
import com.sinx.task.presentation.TaskDepsStore

class App : Application(), DbProvider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(context = this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        TaskDepsStore.deps = appComponent
    }

    override fun getTaskDAO(): TaskDAO = appComponent.taskDAO
}
