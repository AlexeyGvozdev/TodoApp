package com.sinx.task.presentation

import Feature
import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.sinx.coredbinterface.dao.TaskDAO
import com.sinx.task.TaskListFragment
import dagger.Component
import kotlin.properties.Delegates.notNull

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

interface TaskDeps {
    val taskDAO: TaskDAO
}

interface TaskDepsProvider {
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: TaskDeps

    companion object : TaskDepsProvider by TaskDepsStore
}

object TaskDepsStore : TaskDepsProvider {
    override var deps: TaskDeps by notNull()
}

class TaskComponentViewModel : ViewModel() {
    val newDetailComponent =
        DaggerTaskComponent.builder().deps(TaskDepsProvider.deps).build()
}
