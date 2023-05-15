package com.sinx.task

import android.app.Application
import com.sinx.task.di.DbComponent

class AppTask: Application() {
    lateinit var dbComponent: DbComponent

    override fun onCreate() {
        super.onCreate()
        dbComponent = DaggerDbComponent.create()
    }
}