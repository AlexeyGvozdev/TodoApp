package com.sinx.task.di

import kotlin.properties.Delegates

object TaskDepsStore : TaskDepsProvider {
    override var deps: TaskDeps by Delegates.notNull()
}