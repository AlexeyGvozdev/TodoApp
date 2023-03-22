package com.sinx.task.model

import com.sinx.taskList.TaskItem
import kotlinx.coroutines.flow.Flow

class GetTaskListUseCaseImpl(private val repository: TaskRepository) : GetTaskListUseCase {

    override suspend operator fun invoke() = repository.listTasksFlow()
}

interface GetTaskListUseCase {
    suspend operator fun invoke(): Flow<List<TaskItem>>
}
