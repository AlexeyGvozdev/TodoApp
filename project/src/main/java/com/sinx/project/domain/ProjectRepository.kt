package com.sinx.project.domain

import com.sinx.project.data.ProjectListModel
import kotlinx.coroutines.flow.Flow

internal interface ProjectRepository {

    fun addNewProject(newProject: ProjectListModel)

    suspend fun listTasksFlow(): Flow<List<ProjectListModel>>
}