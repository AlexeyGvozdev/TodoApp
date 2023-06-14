package com.sinx.task.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sinx.task.Constants
import com.sinx.task.R
import com.sinx.task.databinding.BottomSheetPriorityLayoutBinding
import com.sinx.core.R as core_R


class BottomSheetPrioritySheetFragment:
    BottomSheetDialogFragment(R.layout.bottom_sheet_priority_layout) {

    private var _binding: BottomSheetPriorityLayoutBinding? = null
    private val binding: BottomSheetPriorityLayoutBinding
        get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetPriorityLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.priorityGreen.setOnClickListener { setPriority("Green") }
        binding.priorityRed.setOnClickListener { setPriority("Red") }
        binding.priorityWithoutPriority.setOnClickListener { setPriority("Without") }
    }

    private fun setPriority(priority: String) {
        setFragmentResult(
            Constants.SET_PRIORITY_REQUEST_KEY,
            bundleOf(Constants.SET_PRIORITY_BUNDLE_KEY to priority)
        )
        dismiss()
    }

    override fun getTheme(): Int {
        return core_R.style.BottomSheetDialogTheme
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}