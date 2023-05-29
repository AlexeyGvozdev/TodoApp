package com.sinx.task.di

import android.content.Context
import com.sinx.core.Feature
import com.sinx.task.TaskListFragment
import dagger.BindsInstance
import dagger.Component

@Feature
@Component(dependencies = [TaskDeps::class])
interface TaskComponent {

    fun inject(fragment: TaskListFragment)

    @Component.Builder
    interface Builder {

        fun deps(taskDeps: TaskDeps): Builder

        fun build(): TaskComponent
    }
}