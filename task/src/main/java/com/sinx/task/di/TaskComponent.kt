package com.sinx.task.di

import com.sinx.core.Feature
import com.sinx.task.TaskListFragment
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