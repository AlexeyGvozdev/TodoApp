package com.sinx.taskList.data

import com.sinx.coredbinterface.entity.TaskDbModel
import com.sinx.taskList.TaskItem

class Mapper {

    fun mapTaskDbToTaskItem(taskDbModel: TaskDbModel) = TaskItem(
        name = taskDbModel.name,
        date = taskDbModel.date,
        enabled = taskDbModel.enabled,
        priority = taskDbModel.priority,
        numberInList = taskDbModel.numberInList
    )

    fun mapTaskItemToTaskDb(taskItem: TaskItem) = TaskDbModel(
        name = taskItem.name,
        date = taskItem.date,
        enabled = taskItem.enabled,
        priority = taskItem.priority,
        numberInList = taskItem.numberInList
    )
}