package com.sinx.task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinx.task.domain.GetProjectListUseCase
import com.sinx.task.domain.ProjectModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

class SelectProjectViewModel (
     private val getProjectList: GetProjectListUseCase
) : ViewModel() {

     private var _projectList = MutableSharedFlow<List<ProjectModel>>(
          replay = 1,
          onBufferOverflow = BufferOverflow.DROP_LATEST
     )
     val projectList: SharedFlow<List<ProjectModel>> = _projectList

     val searchString = MutableSharedFlow<String>(
          replay = 1,
     onBufferOverflow = BufferOverflow.DROP_LATEST
     )

     fun initialize() {
          viewModelScope.launch {
               try {
                    _projectList.emitAll(getProjectList())
               } catch (e: IllegalStateException) {
                    e.printStackTrace()
               }
               searchString.collectLatest {

               }
          }
     }
}