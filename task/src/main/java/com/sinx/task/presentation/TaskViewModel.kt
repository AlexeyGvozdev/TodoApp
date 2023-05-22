package com.sinx.task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sinx.coredbinterface.dao.TaskDAO
import com.sinx.taskList.TaskItem
import com.sinx.taskList.data.TaskRepositoryImpl
import com.sinx.taskList.model.GetTaskListUseCaseImpl
import com.sinx.taskList.model.TaskReadyUseCaseImpl
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class TaskViewModel(private val taskDAO: TaskDAO) : ViewModel() {

    private val repository = TaskRepositoryImpl(taskDAO)

    private val getTaskListUseCase = GetTaskListUseCaseImpl(repository)
    private val taskReadyUseCase = TaskReadyUseCaseImpl(repository)

    private var _taskList =
        MutableSharedFlow<List<TaskItem>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    val taskList: SharedFlow<List<TaskItem>> = _taskList

    fun initialize() {
        viewModelScope.launch {
            try {
                _taskList.emitAll(getTaskListUseCase())
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
        }
    }

    suspend fun taskIsDone(item: TaskItem) {
        taskReadyUseCase(item)
    }

    class Factory @Inject constructor(
        private val taskDAO: Provider<TaskDAO>
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == TaskViewModel::class.java)
            return TaskViewModel(taskDAO.get()) as T
        }
    }
}