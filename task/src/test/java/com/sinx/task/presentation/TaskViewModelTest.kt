package com.sinx.task.presentation

import com.sinx.taskList.TaskItem
import com.sinx.taskList.model.GetTaskListUseCaseImpl
import com.sinx.taskList.model.TaskReadyUseCaseImpl
import com.sinx.taskList.model.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class TaskViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun checkInitSendListItems() {
//        begin
        val repo = object : TaskRepository {
            override fun taskReady(item: TaskItem) {
//                детект ругаеться
            }

            override suspend fun listTasksFlow(): Flow<List<TaskItem>> {
                return flow {
                    emit(listItems)
                }
            }
        }
        val getTaskListUseCase = GetTaskListUseCaseImpl(repo)
        val taskReadyUseCase = TaskReadyUseCaseImpl(repo)
        val viewModel = TaskViewModel(getTaskListUseCase, taskReadyUseCase)
//        when
        viewModel.initialize()
        val actual = viewModel.taskList.replayCache
//      then
        assert(actual.size == 1)
        assert(actual.first() == this.listItems)
    }

    @Test
    fun checkTaskIsDone() {
//        begin
        var actualItem: TaskItem? = null
        var actualList: List<TaskItem>? = null
        val repo = object : TaskRepository {
            override fun taskReady(item: TaskItem) {
                actualItem = item
            }

            override suspend fun listTasksFlow(): Flow<List<TaskItem>> {
                return flow {
                    if (actualList != null) {
                        emit(listItems)
                    }
                    emit(
                        listOf(
                            TaskItem("Task Manager 1", "\"07 Jan 23 / Project\"", true, 1),
                            TaskItem("Task Manager 0", "\"07 Jan 23 / Project\"", false, 1)
                        )
                    )
                }
            }
        }
        val getTaskListUseCase = GetTaskListUseCaseImpl(repo)
        val taskReadyUseCase = TaskReadyUseCaseImpl(repo)
        val viewModel = TaskViewModel(getTaskListUseCase, taskReadyUseCase)
//        when

        viewModel.initialize()
        viewModel.taskIsDone(listItems.first(), true)
        viewModel.initialize()

        actualList = viewModel.taskList.replayCache.first()
        val expectedList = listOf(
            TaskItem("Task Manager 1", "\"07 Jan 23 / Project\"", true, 1),
            TaskItem("Task Manager 0", "\"07 Jan 23 / Project\"", false, 1)
        )
        val expectedItem = TaskItem("Task Manager 0", "\"07 Jan 23 / Project\"", false, 1)
//        then
        Assert.assertEquals(expectedItem, actualItem)
        Assert.assertEquals(expectedList, actualList)
    }

    val listItems = listOf(
        TaskItem("Task Manager 0", "\"07 Jan 23 / Project\"", true, 1),
        TaskItem("Task Manager 1", "\"07 Jan 23 / Project\"", true, 1)
    )
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}