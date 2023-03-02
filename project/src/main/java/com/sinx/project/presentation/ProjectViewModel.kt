package com.sinx.project.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sinx.project.domain.AddNewProjectUseCase
import com.sinx.project.domain.GetNewProjectUseCase

class ProjectViewModel(
    private val addNewProjectUseCase: AddNewProjectUseCase,
    private val getNewProjectUseCase: GetNewProjectUseCase
) : ViewModel() {

    init {
        Log.d("AAA", "Создали вм")
    }
}