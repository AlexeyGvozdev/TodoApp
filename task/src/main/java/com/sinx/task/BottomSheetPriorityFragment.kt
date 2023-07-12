package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sinx.task.Constants.GREEN_PRIORITY
import com.sinx.task.Constants.LIGHT_GREY_PRIORITY
import com.sinx.task.Constants.RED_PRIORITY
import com.sinx.task.Constants.SET_PRIORITY_BUNDLE_KEY
import com.sinx.task.Constants.SET_PRIORITY_REQUEST_KEY
import com.sinx.task.databinding.BottomSheetPriorityLayoutBinding
import com.sinx.core.R as core_R

class BottomSheetPriorityFragment :
    BottomSheetDialogFragment(R.layout.bottom_sheet_priority_layout) {

    private var _binding: BottomSheetPriorityLayoutBinding? = null
    private val binding get() = checkNotNull(_binding)

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
        binding.buttonWithoutPriority.setOnClickListener { setPriority(LIGHT_GREY_PRIORITY) }
        binding.buttonGreenPriority.setOnClickListener { setPriority(GREEN_PRIORITY) }
        binding.buttonRedPriority.setOnClickListener { setPriority(RED_PRIORITY) }
    }

    private fun setPriority(priority: String) {
        setFragmentResult(
            SET_PRIORITY_REQUEST_KEY,
            bundleOf(SET_PRIORITY_BUNDLE_KEY to priority)
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
