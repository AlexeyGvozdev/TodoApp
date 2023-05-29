package com.sinx.project.domain

import com.sinx.coredbinterface.entity.ProjectDbModel
import kotlinx.coroutines.flow.Flow

internal interface ProjectRepository {

    suspend fun addNewProject(newProject: ProjectDbModel)

    suspend fun listTasksFlow(): Flow<List<ProjectDbModel>>
}