package com.sinx.task.di

import com.sinx.coreDB.di.DiDbModule
import com.sinx.task.TaskListFragment
import dagger.Component

@Component(modules = [DiDbModule::class])
interface DbComponent {
    fun inject(fragment: TaskListFragment)
}