package com.sinx.todo

import com.sinx.coreDB.di.DbModule
import com.sinx.task.TaskListFragment
import dagger.Component

@Component(modules = [DbModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)

}

