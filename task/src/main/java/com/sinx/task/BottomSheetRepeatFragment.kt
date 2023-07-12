package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sinx.task.databinding.BottomSheetRepeatBinding
import com.sinx.core.R as core_R

class BottomSheetRepeatFragment :
    BottomSheetDialogFragment(R.layout.bottom_sheet_repeat) {

    private var _binding: BottomSheetRepeatBinding? = null
    private val binding: BottomSheetRepeatBinding
        get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetRepeatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            selectdays.setOnClickListener {
                linearLayout.isInvisible = !selectdays.isChecked
            }
        }
    }

    override fun getTheme(): Int {
        return core_R.style.BottomSheetDialogTheme
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}