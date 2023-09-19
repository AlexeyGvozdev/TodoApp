package com.sinx.task.di

import com.sinx.core.di.ComponentDependencies
import com.sinx.coredbinterface.dao.ProjectDAO
import com.sinx.coredbinterface.dao.TaskDAO

interface TaskDeps : ComponentDependencies {
    val taskDAO: TaskDAO
    val projectDAO: ProjectDAO
}