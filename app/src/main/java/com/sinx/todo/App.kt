package com.sinx.todo

import android.app.Application
import android.content.Context
import com.sinx.coreDB.di.DbModule
import com.sinx.coredbinterface.DbProvider
import com.sinx.coredbinterface.dao.TaskDAO

class App : Application(), DbProvider {

    lateinit var appComponent: AppComponent
    private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()

        
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }