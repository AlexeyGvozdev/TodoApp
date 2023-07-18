package com.sinx.task.model

data class CheckRepeatButton(
    var isDayMon: Boolean = false,
    var isDayTue: Boolean = false,
    var isDayWed: Boolean = false,
    var isDayThu: Boolean = false,
    var isDayFri: Boolean = false,
    var isDaySat: Boolean = false,
    var isDaySun: Boolean = false,
    var isEveryDay: Boolean = false,
    var isNo: Boolean = true,
    var isOnce: Boolean = false,
    var isOnWeekdays: Boolean = false,
    var isOnWeekends: Boolean = false,
    var isSelectDay: Boolean = false,
    var isDayVisible: Boolean = true
) {
    fun clearDay() {
        isDayMon = false
        isDayTue = false
        isDayWed = false
        isDayThu = false
        isDayFri = false
        isDaySat = false
        isDaySun = false
        isSelectDay = false
        isDayVisible = true
    }

    fun clearRepeat() {
        isEveryDay = false
        isNo = false
        isOnce = false
        isOnWeekdays = false
        isOnWeekends = false
    }
}