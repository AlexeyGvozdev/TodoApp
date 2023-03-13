package com.sinx.project.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sinx.project.data.ProjectRepositoryImpl
import com.sinx.project.domain.AddNewProjectUseCaseImpl
import com.sinx.project.domain.GetNewProjectUseCaseImpl

class ProjectViewModelFactory : ViewModelProvider.Factory {

    private val repository = ProjectRepositoryImpl()

    private val addNewProjectUseCaseImpl = AddNewProjectUseCaseImpl(repository)
    private val getNewProjectUseCase = GetNewProjectUseCaseImpl(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProjectViewModel(addNewProjectUseCaseImpl, getNewProjectUseCase) as T
    }
}