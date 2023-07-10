package com.sinx.todo

import android.content.Context
import com.sinx.core.AppScope
import com.sinx.coreDbImpl.di.DbModule
import com.sinx.task.di.TaskDeps
import dagger.BindsInstance
import dagger.Component

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