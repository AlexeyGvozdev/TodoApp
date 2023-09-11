package com.sinx.task.domain

import com.sinx.coredbinterface.entity.ProjectDbModel
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun listTasksFlow(): Flow<List<ProjectDbModel>>
}