package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sinx.task.Constants.DAY_FRI
import com.sinx.task.Constants.DAY_MON
import com.sinx.task.Constants.DAY_SAT
import com.sinx.task.Constants.DAY_SUN
import com.sinx.task.Constants.DAY_THU
import com.sinx.task.Constants.DAY_TUE
import com.sinx.task.Constants.DAY_WED
import com.sinx.task.Constants.SELECT_DAYS
import com.sinx.task.Constants.SET_REPEAT_EVERYDAY
import com.sinx.task.Constants.SET_REPEAT_NO
import com.sinx.task.Constants.SET_REPEAT_ONCE
import com.sinx.task.Constants.SET_REPEAT_ONWEEKDAYS
import com.sinx.task.Constants.SET_REPEAT_ONWEEKENDS
import com.sinx.task.databinding.BottomSheetRepeatBinding
import com.sinx.task.presentation.SheetRepeatViewModel
import com.sinx.core.R as core_R

class BottomSheetRepeatFragment :
    BottomSheetDialogFragment() {

    private var _binding: BottomSheetRepeatBinding? = null
    private val binding: BottomSheetRepeatBinding
        get() = checkNotNull(_binding)

    private val viewModel: SheetRepeatViewModel by activityViewModels()

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
        lifecycleScope.launchWhenStarted {
            viewModel.checkRepeatBinding.collect() {
                binding.monday.isChecked = it.isDayMon
                binding.tuesday.isChecked = it.isDayTue
                binding.wednesday.isChecked = it.isDayWed
                binding.thursday.isChecked = it.isDayThu
                binding.friday.isChecked = it.isDayFri
                binding.saturday.isChecked = it.isDaySat
                binding.sunday.isChecked = it.isDaySun
                binding.chipNo.isChecked = it.isNo
                binding.chipOnce.isChecked = it.isOnce
                binding.chipEveryDay.isChecked = it.isEveryDay
                binding.chipOnWeekdays.isChecked = it.isOnWeekdays
                binding.chipOnWeekends.isChecked = it.isOnWeekends
                binding.selectDays.isChecked = it.isSelectDay
                binding.linearLayout.isInvisible = !binding.selectDays.isChecked
            }
        }
        binding.chipNo.setOnClickListener { viewModel.onClickRepeat(SET_REPEAT_NO) }
        binding.chipOnce.setOnClickListener { viewModel.onClickRepeat(SET_REPEAT_ONCE) }
        binding.chipEveryDay.setOnClickListener { viewModel.onClickRepeat(SET_REPEAT_EVERYDAY) }
        binding.chipOnWeekdays.setOnClickListener { viewModel.onClickRepeat(SET_REPEAT_ONWEEKDAYS) }
        binding.chipOnWeekends.setOnClickListener { viewModel.onClickRepeat(SET_REPEAT_ONWEEKENDS) }
        binding.selectDays.setOnClickListener {
            viewModel.onClickRepeat(SELECT_DAYS)
            binding.linearLayout.isInvisible = !binding.selectDays.isChecked
        }
        binding.monday.setOnClickListener { onClickDay(DAY_MON) }
        binding.tuesday.setOnClickListener { onClickDay(DAY_TUE) }
        binding.wednesday.setOnClickListener { onClickDay(DAY_WED) }
        binding.thursday.setOnClickListener { onClickDay(DAY_THU) }
        binding.friday.setOnClickListener { onClickDay(DAY_FRI) }
        binding.saturday.setOnClickListener { onClickDay(DAY_SAT) }
        binding.sunday.setOnClickListener { onClickDay(DAY_SUN) }
        binding.bottomSheetRepeatOkButton.setOnClickListener {
            viewModel.checkRepeatString()
            dismiss()
        }
    }

    private fun onClickDay(day: String) {
        viewModel.onClickRepeat(day)
    }

    override fun getTheme(): Int {
        return core_R.style.BottomSheetDialogTheme
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}