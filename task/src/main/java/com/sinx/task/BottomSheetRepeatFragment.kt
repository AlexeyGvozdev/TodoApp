package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.linearLayout.visibility = View.INVISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            selectdays.setOnClickListener{
                if (selectdays.isChecked) {
                    linearLayout.visibility = View.VISIBLE
                }
                else linearLayout.visibility = View.INVISIBLE
            }
        }
    }

    override fun getTheme(): Int {
        return core_R.style.BottomSheetDialogTheme
    }
}