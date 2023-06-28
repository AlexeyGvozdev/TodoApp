package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sinx.task.Constants.DAY_FRI
import com.sinx.task.Constants.DAY_MON
import com.sinx.task.Constants.DAY_SAT
import com.sinx.task.Constants.DAY_SUN
import com.sinx.task.Constants.DAY_THU
import com.sinx.task.Constants.DAY_TUE
import com.sinx.task.Constants.DAY_WED
import com.sinx.task.Constants.SET_REPEAT_EVERYDAY
import com.sinx.task.Constants.SET_REPEAT_NO
import com.sinx.task.Constants.SET_REPEAT_ONCE
import com.sinx.task.Constants.SET_REPEAT_ONWEEKDAYS
import com.sinx.task.Constants.SET_REPEAT_ONWEEKENDS
import com.sinx.task.databinding.BottomSheetRepeatBinding
import com.sinx.task.presentation.SheetRepeatViewModel
import com.sinx.core.R as core_R

class BottomSheetRepeatFragment :
    BottomSheetDialogFragment(R.layout.bottom_sheet_repeat) {

    private var _binding: BottomSheetRepeatBinding? = null
    private val binding: BottomSheetRepeatBinding
        get() = checkNotNull(_binding)

    private val viewModel: SheetRepeatViewModel by viewModels()

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
        setFragmentResultListener(Constants.SET_REPEAT_REQUEST_KEY) { _, bundle ->
            var resultRepeat = bundle.getStringArray(Constants.SET_REPEAT_BUNDLE_KEY)?.toList()
            viewModel.checkRepeat.clear()
            viewModel.checkRepeat = resultRepeat?.toMutableSet() ?: mutableSetOf()
            if (resultRepeat != null) setCheckedRepeat(resultRepeat)
        }
        chipDayOnClickListener()
        chipOnClickListener()
    }

    private fun setCheckedRepeat(resultRepeat: List<String>) {
        when {
            SET_REPEAT_NO in resultRepeat -> binding.chipNo.isChecked = true
            SET_REPEAT_EVERYDAY in resultRepeat -> binding.chipEveryDay.isChecked = true
            SET_REPEAT_ONCE in resultRepeat -> binding.chipOnce.isChecked = true
            SET_REPEAT_ONWEEKDAYS in resultRepeat -> binding.chipOnWeekdays.isChecked = true
            SET_REPEAT_ONWEEKENDS in resultRepeat -> binding.chipOnWeekends.isChecked = true
        }
        if (viewModel.days.any { it in resultRepeat }) {
            binding.linearLayout.isInvisible = false
            binding.selectDays.isChecked = true
        }
        if (DAY_MON in resultRepeat) binding.monday.isChecked = true
        if (DAY_TUE in resultRepeat) binding.tuesday.isChecked = true
        if (DAY_WED in resultRepeat) binding.wednesday.isChecked = true
        if (DAY_THU in resultRepeat) binding.thursday.isChecked = true
        if (DAY_FRI in resultRepeat) binding.friday.isChecked = true
        if (DAY_SAT in resultRepeat) binding.saturday.isChecked = true
        if (DAY_SUN in resultRepeat) binding.sunday.isChecked = true
    }

    fun clearDays() {
        binding.monday.isChecked = false
        binding.tuesday.isChecked = false
        binding.wednesday.isChecked = false
        binding.thursday.isChecked = false
        binding.friday.isChecked = false
        binding.saturday.isChecked = false
        binding.sunday.isChecked = false
        binding.linearLayout.isInvisible = true
        binding.selectDays.isChecked = false
    }

    fun chipDayOnClickListener() {
        binding.selectDays.setOnClickListener {
            binding.linearLayout.isInvisible = !binding.selectDays.isChecked
        }
        binding.monday.setOnClickListener {
            viewModel.setDays(DAY_MON)
            binding.monday.isChecked = !binding.monday.isChecked
        }
        binding.tuesday.setOnClickListener {
            viewModel.setDays(DAY_TUE)
            binding.tuesday.isChecked = !binding.tuesday.isChecked
        }
        binding.wednesday.setOnClickListener {
            viewModel.setDays(DAY_WED)
            binding.wednesday.isChecked = !binding.wednesday.isChecked
        }
        binding.thursday.setOnClickListener {
            viewModel.setDays(DAY_THU)
            binding.thursday.isChecked = !binding.thursday.isChecked
        }
        binding.friday.setOnClickListener {
            viewModel.setDays(DAY_FRI)
            binding.friday.isChecked = !binding.friday.isChecked
        }
        binding.saturday.setOnClickListener {
            viewModel.setDays(DAY_SAT)
            binding.saturday.isChecked = !binding.saturday.isChecked
        }
        binding.sunday.setOnClickListener {
            viewModel.setDays(DAY_SUN)
            binding.sunday.isChecked = !binding.sunday.isChecked
        }
    }

    fun chipOnClickListener() {
        binding.chipNo.setOnClickListener {
            viewModel.setDays(SET_REPEAT_NO)
            clearDays()
        }
        binding.chipEveryDay.setOnClickListener {
            viewModel.setDays(SET_REPEAT_EVERYDAY)
            clearDays()
        }
        binding.chipOnce.setOnClickListener {
            viewModel.setDays(SET_REPEAT_ONCE)
            clearDays()
        }
        binding.chipOnWeekdays.setOnClickListener {
            viewModel.setDays(SET_REPEAT_ONWEEKDAYS)
            clearDays()
        }
        binding.chipOnWeekends.setOnClickListener {
            viewModel.setDays(SET_REPEAT_ONWEEKENDS)
            clearDays()
        }
        binding.bottomSheetRepeatOkButton.setOnClickListener {
            setFragmentResult(
                Constants.GET_REPEAT_REQUEST_KEY,
                bundleOf(Constants.GET_REPEAT_BUNDLE_KEY to viewModel.checkRepeat.toTypedArray())
            )
            dismiss()
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