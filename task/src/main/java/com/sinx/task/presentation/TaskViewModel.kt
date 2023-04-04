package com.sinx.task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinx.taskList.TaskItem
import com.sinx.taskList.model.GetTaskListUseCase
import com.sinx.taskList.model.TaskReadyUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

class TaskViewModel(
    private val getTaskListUseCase: GetTaskListUseCase,
    private val taskReadyUseCase: TaskReadyUseCase
) : ViewModel() {

    private var _taskList =
        MutableSharedFlow<List<TaskItem>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    val taskList: SharedFlow<List<TaskItem>> = _taskList

    fun initialize() {
        viewModelScope.launch {
            try {
                _taskList.emitAll(getTaskListUseCase())
            } catch (e: Exception){

            }

        }
    }

    suspend fun taskIsDone(item: TaskItem) {
        taskReadyUseCase(item)
    }
}