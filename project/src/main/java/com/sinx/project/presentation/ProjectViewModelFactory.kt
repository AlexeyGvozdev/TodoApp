package com.sinx.project.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sinx.coredbinterface.dao.ProjectDAO
import com.sinx.project.data.ProjectRepositoryImpl
import com.sinx.project.domain.AddNewProjectUseCaseImpl
import com.sinx.project.domain.GetNewProjectUseCaseImpl

class ProjectViewModelFactory(private val projectDAO: ProjectDAO) : ViewModelProvider.Factory {

    private val repository = ProjectRepositoryImpl(projectDAO)

    private val addNewProjectUseCase = AddNewProjectUseCaseImpl(repository)
    private val getNewProjectUseCase = GetNewProjectUseCaseImpl(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProjectViewModel(addNewProjectUseCase, getNewProjectUseCase) as T
    }
}