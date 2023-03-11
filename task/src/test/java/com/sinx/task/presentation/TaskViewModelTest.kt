package com.sinx.task.presentation

import com.sinx.task.model.GetTaskListUseCaseImpl
import com.sinx.task.model.TaskItem
import com.sinx.task.model.TaskReadyUseCaseImpl
import com.sinx.task.model.TaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.junit.rules.TestWatcher


class TaskViewModelTest {



    @Test
    fun checkInitSendListItems() {
//        begin
        val repo = object : TaskRepository {
            override fun taskReady(item: TaskItem) {}

            override suspend fun listTasksFlow(): Flow<List<TaskItem>> {
                return flowOf(listItems)
            }
        }
        val getTaskListUseCase = GetTaskListUseCaseImpl(repo)
        val taskReadyUseCase = TaskReadyUseCaseImpl(repo)
//        when
        val viewModel = TaskViewModel(getTaskListUseCase, taskReadyUseCase)

        val actual = viewModel.taskList.replayCache.first()
//      then
        assert(actual.size == 1)
    }

//    @Test
//    fun taskIsDone() {
//    }

    val listItems = listOf(
        TaskItem(0, "Task Manager 0", "\"07 Jan 23 / Project\"", true, 1),
        TaskItem(1, "Task Manager 1", "\"07 Jan 23 / Project\"", true, 1)
    )
}