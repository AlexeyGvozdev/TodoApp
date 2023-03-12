package com.sinx.project.domain

import com.sinx.project.data.ProjectListModel
import kotlinx.coroutines.flow.Flow

class GetNewProjectUseCaseImpl(private val projectRepository: ProjectRepository) :
    GetNewProjectUseCase {

    override suspend fun invoke(): Flow<List<ProjectListModel>> = projectRepository.listTasksFlow()
}

interface GetNewProjectUseCase {

    suspend operator fun invoke(): Flow<List<ProjectListModel>>
}