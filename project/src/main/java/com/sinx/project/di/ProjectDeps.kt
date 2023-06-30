package com.sinx.project.di

import com.sinx.core.di.ComponentDependencies
import com.sinx.coredbinterface.dao.ProjectDAO

interface ProjectDeps : ComponentDependencies {
    val projectDAO: ProjectDAO
}