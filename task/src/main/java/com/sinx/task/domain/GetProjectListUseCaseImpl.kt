package com.sinx.task.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class GetProjectListUseCaseImpl(
    private val projectRepository: TaskRepository,
    private val dispatchers: CoroutineDispatcher = Dispatchers.IO
) : GetProjectListUseCase {

    override suspend fun invoke(text: String): Flow<List<ProjectModel>> = withContext(dispatchers) {
        projectRepository.listTasksFlow(text).map { list ->
            list.map { item ->
                item.toDomain()
            }
        }
    }
}

interface GetProjectListUseCase {

    suspend operator fun invoke(text: String): Flow<List<ProjectModel>>
}