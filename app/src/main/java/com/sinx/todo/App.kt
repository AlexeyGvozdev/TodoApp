package com.sinx.todo

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.sinx.coreDB.di.DbModule
import com.sinx.coredbinterface.DbProvider
import com.sinx.coredbinterface.dao.TaskDAO
import com.sinx.task.presentation.TaskDepsStore
import javax.inject.Scope

class App : Application()/*, DbProvider*/ {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(context = this)
            .build()
    }
    //private set

    override fun onCreate() {
        super.onCreate()
        TaskDepsStore.deps = appComponent

    }

/*    override fun getTaskDAO(): TaskDAO {
        TODO("Not yet implemented")
    }*/
}

/*
val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }

val Fragment.appComponent: AppComponent
    get() = requireContext().applicationContext.appComponent

*/
