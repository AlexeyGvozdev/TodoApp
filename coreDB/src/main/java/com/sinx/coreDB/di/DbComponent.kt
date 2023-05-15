package com.sinx.coreDB.di

import dagger.Component

@Component(modules = [DiDbModule::class])
interface DbComponent {
    //fun inject(fragment: TaskListFragment)
}