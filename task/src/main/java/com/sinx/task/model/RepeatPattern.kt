package com.sinx.task.model

import kotlin.reflect.KMutableProperty0

enum class RepeatPattern {
    Mon, Tue, Wed, Thu, Fri, Sat, Sun,
    No, EveryDay, Once, OnWeekdays, OnWeekends, SelectDays;

    fun toBooleanField(repeatValue: CheckRepeatButton): KMutableProperty0<Boolean> = when (this) {
        Mon -> repeatValue::isDayMon
        Tue -> repeatValue::isDayTue
        Wed -> repeatValue::isDayWed
        Thu -> repeatValue::isDayThu
        Fri -> repeatValue::isDayFri
        Sat -> repeatValue::isDaySat
        Sun -> repeatValue::isDaySun

        No -> repeatValue::isNo
        EveryDay -> repeatValue::isEveryDay
        Once -> repeatValue::isOnce
        OnWeekdays -> repeatValue::isOnWeekdays
        OnWeekends -> repeatValue::isOnWeekends
        SelectDays -> repeatValue::isSelectDay
    }
}
