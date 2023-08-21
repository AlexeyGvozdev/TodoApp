package com.sinx.project.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class GetNewProjectUseCaseImpl(
    private val projectRepository: ProjectRepository,
    private val dispatchers: CoroutineDispatcher = Dispatchers.IO
) : GetNewProjectUseCase {

    override suspend fun invoke(): Flow<List<ProjectListModel>> = withContext(dispatchers) {
        projectRepository.listTasksFlow().map { list ->
            list.map { item ->
                item.toDomain()
            }
        }
    }
}

internal interface GetNewProjectUseCase {

    suspend operator fun invoke(): Flow<List<ProjectListModel>>
}