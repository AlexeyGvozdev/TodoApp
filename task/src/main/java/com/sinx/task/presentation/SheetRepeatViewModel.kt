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
import com.sinx.task.model.CheckRepeatButton
import com.sinx.task.model.RepeatPattern
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class SheetRepeatViewModel : ViewModel() {

    private var _checkRepeat =
        MutableSharedFlow<List<String>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val checkRepeat: SharedFlow<List<String>> = _checkRepeat

    private var _checkRepeatBinding = MutableSharedFlow<CheckRepeatButton>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val checkRepeatBinding: SharedFlow<CheckRepeatButton> = _checkRepeatBinding

    init {
        _checkRepeatBinding.tryEmit(
            CheckRepeatButton()
        )
    }

    private val strRepeat = listOf(
        SET_REPEAT_NO,
        SET_REPEAT_EVERYDAY,
        SET_REPEAT_ONCE,
        SET_REPEAT_ONWEEKDAYS,
        SET_REPEAT_ONWEEKENDS
    )

    fun onClickRepeat(strRepeat: String) {
        val repeatValue = currentRepeatValue ?: return
        repeatValue.clearRepeat()
        if (strRepeat in this.strRepeat) repeatValue.clearDay()
        val repeatField = RepeatPattern.valueOf(strRepeat).toBooleanField(repeatValue)
        repeatField.set(!repeatField.get())
        _checkRepeatBinding.tryEmit(repeatValue)
    }

    fun checkRepeatString() {
        val result: MutableList<String> = mutableListOf()
        val repeatValue = currentRepeatValue ?: return
        when {
            repeatValue.isNo -> result.add(SET_REPEAT_NO)
            repeatValue.isEveryDay -> result.add(SET_REPEAT_EVERYDAY)
            repeatValue.isOnce -> result.add(SET_REPEAT_ONCE)
            repeatValue.isOnWeekdays -> result.add(SET_REPEAT_ONWEEKDAYS)
            repeatValue.isOnWeekends -> result.add(SET_REPEAT_ONWEEKENDS)
        }
        if (repeatValue.isDayMon) result.add(DAY_MON)
        if (repeatValue.isDayTue) result.add(DAY_TUE)
        if (repeatValue.isDayWed) result.add(DAY_WED)
        if (repeatValue.isDayThu) result.add(DAY_THU)
        if (repeatValue.isDayFri) result.add(DAY_FRI)
        if (repeatValue.isDaySat) result.add(DAY_SAT)
        if (repeatValue.isDaySun) result.add(DAY_SUN)
        _checkRepeat.tryEmit(result)
    }

    private val currentRepeatValue: CheckRepeatButton?
        get() = _checkRepeatBinding.replayCache.firstOrNull()
}
