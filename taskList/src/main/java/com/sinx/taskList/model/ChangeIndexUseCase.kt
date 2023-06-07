package com.sinx.taskList.model

import com.sinx.taskList.TaskItem

class ChangeIndexUseCaseImpl(private val repository: TaskRepository) : ChangeIndexUseCase {

    override suspend fun invoke(fromPosition: Int, toPosition: Int, taskList: List<TaskItem>): List<TaskItem> {
        val list = taskList.toMutableList()
        val element = list[fromPosition]
        list.removeAt(fromPosition)
        if (toPosition < fromPosition) {
            list.add(toPosition + 1, element)
        } else {
            list.add(toPosition - 1, element)
        }
        val updatedList = list.mapIndexed { index, taskItem ->
            taskItem.copy(numberInList = index)
        }
        repository.updateList(updatedList)
        return updatedList
    }

}

interface ChangeIndexUseCase {
    suspend operator fun invoke(fromPosition: Int, toPosition: Int, taskList: List<TaskItem>): List<TaskItem>
}