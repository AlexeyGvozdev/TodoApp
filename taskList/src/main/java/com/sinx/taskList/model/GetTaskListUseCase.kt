package com.sinx.taskList.model
import com.sinx.taskList.TaskItem
import kotlinx.coroutines.flow.Flow

class GetTaskListUseCaseImpl(private val repository: TaskRepository) : GetTaskListUseCase {
    override suspend operator fun invoke(): Flow<List<TaskItem>> = repository.listTasksFlow()
}

interface GetTaskListUseCase {
    suspend operator fun invoke(): Flow<List<TaskItem>>
}