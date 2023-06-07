package com.sinx.task.presentation

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDeepLinkRequest
import com.sinx.coredbinterface.dao.TaskDAO
import com.sinx.taskList.TaskItem
import com.sinx.taskList.data.TaskRepositoryImpl
import com.sinx.taskList.model.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskViewModel(
    private val getTaskListUseCase: GetTaskListUseCase,
    private val taskReadyUseCase: TaskReadyUseCase,
    private val changeIndexUseCase: ChangeIndexUseCase
) : ViewModel() {

    private var _taskList =
        MutableSharedFlow<List<TaskItem>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_LATEST
        )
    val taskList: SharedFlow<List<TaskItem>> = _taskList

    private var _error =
        MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    val error: SharedFlow<String> = _error

    private val _navDeepLinkRequest = MutableSharedFlow<NavDeepLinkRequest>()
    val navDeepLinkRequest: SharedFlow<NavDeepLinkRequest> = _navDeepLinkRequest

    fun initialize() {
        viewModelScope.launch {
            try {
                _taskList.emitAll(getTaskListUseCase())
            } catch (e: IllegalStateException) {
                e.printStackTrace()
                e.message?.let { _error.emit(it) }
            }
        }
    }

    fun onRowMoved(fromPosition: Int, toPosition: Int) {
        viewModelScope.launch {
            try {
                _taskList.emit(
                    changeIndexUseCase(
                        fromPosition,
                        toPosition,
                        taskList.replayCache.firstOrNull() ?: emptyList()
                    )
                )
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
        }
    }

    suspend fun taskIsDone(item: TaskItem) {
        taskReadyUseCase(item)
    }

    fun onClickListenerBottomSheet() {
        val requestBottomSheetAddTaskFragment = NavDeepLinkRequest.Builder
            .fromUri("app://task.addTaskFragment".toUri())
            .build()
        viewModelScope.launch {
            _navDeepLinkRequest.emit(requestBottomSheetAddTaskFragment)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        taskDAO: TaskDAO
    ) : ViewModelProvider.Factory {

        private val repository = TaskRepositoryImpl(taskDAO)

        private val getTaskListUseCase = GetTaskListUseCaseImpl(repository)
        private val taskReadyUseCase = TaskReadyUseCaseImpl(repository)
        private val changeIndexUseCase = ChangeIndexUseCaseImpl(repository)

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == TaskViewModel::class.java)
            return TaskViewModel(getTaskListUseCase, taskReadyUseCase, changeIndexUseCase) as T
        }
    }
}
