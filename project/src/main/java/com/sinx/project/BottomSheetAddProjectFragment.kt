package com.sinx.project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sinx.project.R
import com.sinx.project.databinding.BottomSheetAddNewProjectBinding
import com.sinx.core.R as core_R

class BottomSheetAddProjectFragment :
    BottomSheetDialogFragment(R.layout.bottom_sheet_add_new_project) {

    private var _binding: BottomSheetAddNewProjectBinding? = null
    private val binding: BottomSheetAddNewProjectBinding
        get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetAddNewProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addProject()
    }

    override fun getTheme(): Int {
        return core_R.style.BottomSheetDialogTheme
    }

    private fun addProject() {
        binding.buttonAddProject.setOnClickListener(View.OnClickListener {
            val nameNewProject = binding.editTextInput.editableText.toString()
            Log.d("test", nameNewProject)
        })
    }

//    fun getNameProject(): String {
//        return binding.editTextInput.editableText.toString()
//
//    }
}