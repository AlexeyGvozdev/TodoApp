package com.sinx.task.presentation

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDeepLinkRequest
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class PrioritySheetViewModel : ViewModel() {

    private val _navDeepLinkRequest = MutableSharedFlow<NavDeepLinkRequest>()
    val navDeepLinkRequest: SharedFlow<NavDeepLinkRequest> = _navDeepLinkRequest

    fun onClickListenerBottomSheet() {
        val requestBottomSheetAddProjectFragment = NavDeepLinkRequest.Builder
            .fromUri("app://task.BottomSheetPrioritySheetFragment".toUri())
            .build()
        viewModelScope.launch {
            _navDeepLinkRequest.emit(requestBottomSheetAddProjectFragment)
        }
    }
}