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
        onClickSelect("app://task.BottomSheetPriorityFragment")
    }

    fun onClickSelectRepeat() {
        onClickSelect("app://task.BottomSheetRepeatFragment")
    }

    private fun onClickSelect(uri: String) {
        val request = NavDeepLinkRequest.Builder
            .fromUri(uri.toUri())
            .build()
        viewModelScope.launch {
            _navDeepLinkRequest.emit(request)
        }
    }
}
