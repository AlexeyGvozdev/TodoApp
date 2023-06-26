package com.sinx.taskList.data

import com.sinx.coredbinterface.dao.TaskDAO
import com.sinx.taskList.TaskItem
import com.sinx.taskList.model.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(private val taskDAO: TaskDAO) : TaskRepository {

    private val mapper = Mapper()

    override suspend fun taskReady(item: TaskItem) {
        taskDAO.addTask(mapper.mapTaskItemToTaskDb(item))
    }

    override suspend fun listTasksFlow(): Flow<List<TaskItem>> {
        return taskDAO.getTaskList().map {
            it.map {
                mapper.mapTaskDbToTaskItem(it)
            }
        }
    }
}