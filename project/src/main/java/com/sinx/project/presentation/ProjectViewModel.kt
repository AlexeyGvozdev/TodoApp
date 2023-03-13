package com.sinx.project.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinx.project.data.ProjectListModel
import com.sinx.project.domain.AddNewProjectUseCaseImpl
import com.sinx.project.domain.GetNewProjectUseCaseImpl
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

class ProjectViewModel(
    private val addNewProjectUseCaseImpl: AddNewProjectUseCaseImpl,
    private val getNewProjectUseCase: GetNewProjectUseCaseImpl
) : ViewModel() {

    private val _projectList = MutableSharedFlow<List<ProjectListModel>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val projectList: SharedFlow<List<ProjectListModel>> = _projectList

    fun initialize() {
        viewModelScope.launch {
            _projectList.emitAll(getNewProjectUseCase())
        }
    }

    fun addNewProject(newProject: ProjectListModel) {
        addNewProjectUseCaseImpl(newProject)
    }
}