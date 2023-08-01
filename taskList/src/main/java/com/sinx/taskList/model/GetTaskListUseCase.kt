package com.sinx.taskList.model

import com.sinx.taskList.TaskItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTaskListUseCaseImpl(private val repository: TaskRepository) : GetTaskListUseCase {

    override suspend operator fun invoke(): Flow<List<TaskItem>>{

        return repository.listTasksFlow().map{
            listOf(
                TaskItem("rrr", "09", true, 0),
                TaskItem("aииоо", "June1", true, 1)
            )
        }
    }
}

interface GetTaskListUseCase {
    suspend operator fun invoke(): Flow<List<TaskItem>>
}