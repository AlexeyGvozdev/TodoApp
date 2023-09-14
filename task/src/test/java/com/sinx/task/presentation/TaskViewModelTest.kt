package com.sinx.task.presentation

import com.sinx.taskList.TaskItem
import com.sinx.taskList.model.ChangeIndexUseCaseImpl
import com.sinx.taskList.model.GetTaskListUseCaseImpl
import com.sinx.taskList.model.TaskReadyUseCaseImpl
import com.sinx.taskList.model.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
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

    val listItems = listOf(
        TaskItem(0, "Task Manager 0", "\"07 Jan 23 / Project\"", true, 1),
        TaskItem(1, "Task Manager 1", "\"07 Jan 23 / Project\"", true, 1)
    )

    @Test
    fun checkInitSendListItems() {
        // begin
        val repo = object : TaskRepository {
            override suspend fun taskReady(item: TaskItem) {
//                детект ругаеться, на пустое тело
            }

            override suspend fun listTasksFlow(): Flow<List<TaskItem>> {
                return flow {
                    emit(listItems)
                }
            }

            override suspend fun updateList(itemList: List<TaskItem>) {
                TODO("Not yet implemented")
            }
        }
        val getTaskListUseCase = GetTaskListUseCaseImpl(repo)
        val taskReadyUseCase = TaskReadyUseCaseImpl(repo)
        val changeIndexUseCase = ChangeIndexUseCaseImpl(repo)
        val viewModel = TaskViewModel(getTaskListUseCase, taskReadyUseCase, changeIndexUseCase)
        // when
        viewModel.initialize()
        val actual = viewModel.taskList.replayCache
        // then
        assert(actual.size == 1)
        assert(actual.first() == this.listItems)
    }

    @Test
    fun checkTaskIsDone() {
        // begin
        val taskFlow = MutableSharedFlow<List<TaskItem>>(replay = 1)
        val scope = CoroutineScope(Dispatchers.Main)
        var actualItem: TaskItem? = null
        val repo = object : TaskRepository {
            override suspend fun taskReady(item: TaskItem) {
                scope.launch {
                    taskFlow.emit(
                        listOf(
                            TaskItem(0, "Task Manager 1", "\"07 Jan 23 / Project\"", true, 1),
                            TaskItem(1, "Task Manager 0", "\"07 Jan 23 / Project\"", false, 1)
                        )
                    )
                }
                actualItem = item
            }

            override suspend fun listTasksFlow(): Flow<List<TaskItem>> {
                scope.launch {
                    taskFlow.emit(
                        listItems
                    )
                }
                return taskFlow
            }

            override suspend fun updateList(itemList: List<TaskItem>) {
                TODO("Not yet implemented")
            }
        }
        val getTaskListUseCase = GetTaskListUseCaseImpl(repo)
        val taskReadyUseCase = TaskReadyUseCaseImpl(repo)
        val changeIndexUseCase = ChangeIndexUseCaseImpl(repo)
        val viewModel = TaskViewModel(getTaskListUseCase, taskReadyUseCase, changeIndexUseCase)
        // when
        viewModel.initialize()
        scope.launch {
            viewModel.taskIsDone(listItems.first())
        }

        val actualList = viewModel.taskList.replayCache.first()
        val expectedList = listOf(
            TaskItem(0, "Task Manager 1", "\"07 Jan 23 / Project\"", true, 1),
            TaskItem(1, "Task Manager 0", "\"07 Jan 23 / Project\"", false, 1)
        )
        val expectedItem = TaskItem(0, "Task Manager 0", "\"07 Jan 23 / Project\"", false, 1)
        // then
        Assert.assertEquals(expectedItem, actualItem)
        Assert.assertEquals(expectedList, actualList)
    }

    @Test
    fun errorHandlingTest() {
        // begin
        val repo = object : TaskRepository {
            override suspend fun taskReady(item: TaskItem) {
                // todo
            }

            override suspend fun listTasksFlow(): Flow<List<TaskItem>> {
                return flow {
                    throw IllegalStateException("some text")
                }
            }

            override suspend fun updateList(itemList: List<TaskItem>) {
                TODO("Not yet implemented")
            }
        }
        val getTaskListUseCase = GetTaskListUseCaseImpl(repo)
        val taskReadyUseCase = TaskReadyUseCaseImpl(repo)
        val changeIndexUseCase = ChangeIndexUseCaseImpl(repo)
        val viewModel = TaskViewModel(getTaskListUseCase, taskReadyUseCase, changeIndexUseCase)
        // when
        viewModel.initialize()
    }
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