package com.sinx.task.di

import androidx.annotation.RestrictTo

interface TaskDepsProvider {
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: TaskDeps

    companion object : TaskDepsProvider by TaskDepsStore
}