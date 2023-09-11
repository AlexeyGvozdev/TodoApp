package com.sinx.task.presentation

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDeepLinkRequest
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class AddTaskViewModel : ViewModel() {

    private val _navDeepLinkRequest = MutableSharedFlow<NavDeepLinkRequest>()
    val navDeepLinkRequest: SharedFlow<NavDeepLinkRequest> = _navDeepLinkRequest

    fun onClickSelectPriority() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("app://task.BottomSheetPriorityFragment".toUri())
            .build()
        viewModelScope.launch {
            _navDeepLinkRequest.emit(request)
        }
    }

    fun onClickSelectRepeat() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("app://task/BottomSheetRepeatFragment".toUri())
            .build()
        viewModelScope.launch {
            _navDeepLinkRequest.emit(request)
        }
    }

    fun onClickSelectProject() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("app://task/BottomSheetChoiceProjectForTask".toUri())
            .build()
        viewModelScope.launch {
            _navDeepLinkRequest.emit(request)
        }
    }
}