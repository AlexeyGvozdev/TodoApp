package com.sinx.project.di

import com.sinx.core.Feature
import com.sinx.project.ProjectFragment
import dagger.Component

@Feature
@Component(dependencies = [ProjectDeps::class])
interface ProjectComponent {

    fun inject(fragment: ProjectFragment)

    @Component.Builder
    interface Builder {

        fun deps(projectDeps: ProjectDeps): Builder

        fun build(): ProjectComponent
    }
}