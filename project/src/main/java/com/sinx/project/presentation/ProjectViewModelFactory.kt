package com.sinx.project.presentation

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import com.sinx.project.data.ProjectRepositoryImpl
import com.sinx.project.domain.AddNewProjectUseCase
import com.sinx.project.domain.GetNewProjectUseCase

class ProjectViewModelFactory: ViewModelProvider.Factory {

    private val repository = ProjectRepositoryImpl()

    private val addNewProjectUseCase = AddNewProjectUseCase(repository)
    private val getNewProjectUseCase = GetNewProjectUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProjectViewModel(addNewProjectUseCase, getNewProjectUseCase) as T
    }
}