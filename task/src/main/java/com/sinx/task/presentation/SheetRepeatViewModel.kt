package com.sinx.task.presentation

import androidx.lifecycle.ViewModel
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

class SheetRepeatViewModel : ViewModel() {

    var checkRepeat = mutableSetOf<String>()

    private val repeat = listOf<String>(
        SET_REPEAT_NO,
        SET_REPEAT_EVERYDAY,
        SET_REPEAT_ONCE,
        SET_REPEAT_ONWEEKDAYS,
        SET_REPEAT_ONWEEKENDS
    )
    val days = listOf<String>(
        DAY_MON,
        DAY_TUE,
        DAY_WED,
        DAY_THU,
        DAY_FRI,
        DAY_SAT,
        DAY_SUN
    )

    fun setDays(day: String) {
        when {
            day in repeat -> {
                checkRepeat.clear()
                checkRepeat.add(day)
            }
            day in checkRepeat -> checkRepeat.remove(day)
            !(day in repeat) && checkRepeat.any { it in repeat } -> {
                checkRepeat.clear()
                checkRepeat.add(day)
            }
            else -> {
                checkRepeat.add(day)
            }
        }
    }
}