package com.sinx.taskList.model

import com.sinx.taskList.TaskItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTaskListUseCaseImpl(private val repository: TaskRepository) : GetTaskListUseCase {
    override suspend operator fun invoke() = repository.listTasksFlow().map {
        it.ifEmpty {
            listOf(
                TaskItem(0, "name1", "asdsas", false, 1)
            )
        }
    }
}

interface GetTaskListUseCase {
    suspend operator fun invoke(): Flow<List<TaskItem>>
}