package com.sinx.task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sinx.coredbinterface.dao.ProjectDAO
import com.sinx.coredbinterface.dao.TaskDAO
import com.sinx.task.data.TaskRepositoryModuleImpl
import com.sinx.task.domain.GetProjectListUseCaseImpl
import com.sinx.taskList.data.TaskRepositoryImpl
import com.sinx.taskList.model.GetTaskListUseCaseImpl
import com.sinx.taskList.model.TaskReadyUseCaseImpl
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class SelectProjectViewModelFactory @Inject constructor(
    projectDAO: ProjectDAO
) : ViewModelProvider.Factory {

    private val repository = TaskRepositoryModuleImpl(projectDAO)

    private val getTaskListUseCase = GetProjectListUseCaseImpl(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == TaskViewModel::class.java)
        return SelectProjectViewModel(getTaskListUseCase) as T
    }
}