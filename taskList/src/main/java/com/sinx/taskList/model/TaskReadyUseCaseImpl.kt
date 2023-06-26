package com.sinx.taskList.model

import com.sinx.taskList.TaskItem

class TaskReadyUseCaseImpl(private val repository: TaskRepository) : TaskReadyUseCase {

    override suspend operator fun invoke(item: TaskItem) {
        val newItem = item.copy(enabled = !item.enabled)
        return repository.taskReady(newItem)
    }
}

interface TaskReadyUseCase {
    suspend operator fun invoke(item: TaskItem)
}