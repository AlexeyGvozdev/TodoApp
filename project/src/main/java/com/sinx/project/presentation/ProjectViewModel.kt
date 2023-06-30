package com.sinx.project.presentation

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDeepLinkRequest
import com.sinx.project.domain.AddNewProjectUseCase
import com.sinx.project.domain.ProjectListModel
import com.sinx.project.domain.GetNewProjectUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

internal class ProjectViewModel(
    private val addNewProjectUseCase: AddNewProjectUseCase,
    private val getNewProjectUseCase: GetNewProjectUseCase
) : ViewModel() {

    private val _projectList = MutableSharedFlow<List<ProjectListModel>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val projectList: SharedFlow<List<ProjectListModel>> = _projectList

    private val _navDeepLinkRequest = MutableSharedFlow<NavDeepLinkRequest>()
    val navDeepLinkRequest: SharedFlow<NavDeepLinkRequest> = _navDeepLinkRequest

    fun initialize() {
        viewModelScope.launch {
            try {
                _projectList.emitAll(getNewProjectUseCase())
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
        }
    }

    fun addNewProject(newProject: ProjectListModel) {
        viewModelScope.launch {
            addNewProjectUseCase(newProject)
        }
    }

    fun onClickAddProject() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("app://project.BottomSheetAddProjectFragment".toUri())
            .build()
        viewModelScope.launch {
            _navDeepLinkRequest.emit(request)
        }
    }

    fun dateCreateProject(): String {
        return SimpleDateFormat("dd MMM yy", Locale.getDefault()).format(Date())
    }
}