package com.sinx.taskList.model

import com.sinx.taskList.TaskItem
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun taskReady(item: TaskItem)

    suspend fun listTasksFlow(): Flow<List<TaskItem>>

    suspend fun updateList(itemList: List<TaskItem>)
}