package com.sinx.task.presentation

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDeepLinkRequest
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
        MutableSharedFlow<List<TaskItem>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_LATEST
        )
    val taskList: SharedFlow<List<TaskItem>> = _taskList

    private val _navDeepLinkRequest = MutableSharedFlow<NavDeepLinkRequest>()
    val navDeepLinkRequest: SharedFlow<NavDeepLinkRequest> = _navDeepLinkRequest

    fun initialize() {
        viewModelScope.launch {
            try {
                _taskList.emitAll(getTaskListUseCase())
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
        }
    }

    fun onClickListenerBottomSheet(){
        val reuestBottomSheetAddTaskFragment = NavDeepLinkRequest.Builder
            .fromUri("app://task.BottomSheetAddTaskFragment".toUri())
            .build()
        viewModelScope.launch {
            _navDeepLinkRequest.emit(reuestBottomSheetAddTaskFragment)
        }
    }

    suspend fun taskIsDone(item: TaskItem) {
        taskReadyUseCase(item)
    }
}