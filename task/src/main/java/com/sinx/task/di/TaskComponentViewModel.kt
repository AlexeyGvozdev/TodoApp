package com.sinx.task.di

import androidx.lifecycle.ViewModel

class TaskComponentViewModel : ViewModel() {
    val newDetailComponent =
        DaggerTaskComponent.builder().deps(TaskDepsProvider.deps).build()
}