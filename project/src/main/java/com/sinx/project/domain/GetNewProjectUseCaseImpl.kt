package com.sinx.project.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class GetNewProjectUseCaseImpl(private val projectRepository: ProjectRepository) :
    GetNewProjectUseCase {

    override suspend fun invoke(): Flow<List<ProjectListModel>> {
        return projectRepository.listTasksFlow().map { list ->
            list.map { item ->
                item.toDomain()
            }
        }
    }
}

internal interface GetNewProjectUseCase {

    suspend operator fun invoke(): Flow<List<ProjectListModel>>
}