package com.sinx.project.domain

import com.sinx.project.data.ProjectListModel
import kotlinx.coroutines.flow.Flow

internal class GetNewProjectUseCaseImpl(private val projectRepository: ProjectRepository) :
    GetNewProjectUseCase {

    override suspend fun invoke(): Flow<List<ProjectListModel>> = projectRepository.listTasksFlow()
}

internal interface GetNewProjectUseCase {

    suspend operator fun invoke(): Flow<List<ProjectListModel>>
}