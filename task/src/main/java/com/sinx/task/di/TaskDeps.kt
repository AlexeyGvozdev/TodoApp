package com.sinx.task.di

import com.sinx.coredbinterface.dao.TaskDAO

interface TaskDeps {
    val taskDAO: TaskDAO
}