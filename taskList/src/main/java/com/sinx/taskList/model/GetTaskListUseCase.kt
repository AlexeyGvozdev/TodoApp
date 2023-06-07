package com.sinx.taskList.model

import com.sinx.taskList.TaskItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTaskListUseCaseImpl(private val repository: TaskRepository) : GetTaskListUseCase {

    override suspend operator fun invoke(): Flow<List<TaskItem>> {
        return repository.listTasksFlow().map {
            it.ifEmpty {
                listOf(
                    TaskItem("1", "08", true, 0),
                    TaskItem("2", "08", true, 0),
                    TaskItem("3", "08", true, 0),
                    TaskItem("4", "08", true, 0),
                    TaskItem("5", "08", true, 0),
                    TaskItem("6", "08", true, 0),
                    TaskItem("7", "08", true, 0)
                )
            }.mapIndexed { index, taskItem ->
                taskItem.copy(numberInList = index)
            }.also { taskItems ->
                taskItems.map { repository.taskReady(it) } }
        }
    }
}

interface GetTaskListUseCase {
    suspend operator fun invoke(): Flow<List<TaskItem>>
}
