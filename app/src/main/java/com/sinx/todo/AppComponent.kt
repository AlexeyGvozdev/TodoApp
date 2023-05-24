package com.sinx.todo

import AppScope
import android.content.Context
import com.sinx.coreDB.di.DbModule
import com.sinx.task.presentation.TaskDeps
import dagger.BindsInstance
import dagger.Component
import dagger.Component.Builder

@AppScope
@Component(modules = [DbModule::class])
interface AppComponent : TaskDeps {
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}