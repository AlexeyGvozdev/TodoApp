package com.sinx.taskList.model

import com.sinx.taskList.TaskItem
import java.util.*

class ChangeIndexUseCaseImpl(private val repository: TaskRepository) : ChangeIndexUseCase {

    override suspend fun invoke(fromPosition: Int, toPosition: Int, taskList: List<TaskItem>) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(taskList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(taskList, i, i - 1)
            }
        }
        val updatedList = taskList.mapIndexed { index, taskItem ->
            taskItem.copy(numberInList = index)
        }
        repository.updateList(updatedList)
    }
}

interface ChangeIndexUseCase {
    suspend operator fun invoke(fromPosition: Int, toPosition: Int, taskList: List<TaskItem>)
}