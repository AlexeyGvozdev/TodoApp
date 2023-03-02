package com.sinx.project.domain

import com.sinx.project.data.ProjectListModel
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    suspend fun listTasksFlow(): Flow<List<ProjectListModel>>
}