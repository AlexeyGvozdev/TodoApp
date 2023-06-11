package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sinx.task.databinding.BottomSheetChoiceProjectForTaskBinding

class BottomSheetChoiceProjectForTask:
    BottomSheetDialogFragment(R.layout.bottom_sheet_choice_project_for_task) {

    private var _binding: BottomSheetChoiceProjectForTaskBinding? = null
    private val binding: BottomSheetChoiceProjectForTaskBinding
        get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetChoiceProjectForTaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getTheme(): Int {
        return com.sinx.core.R.style.BottomSheetDialogTheme
    }
}