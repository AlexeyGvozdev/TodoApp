package com.sinx.taskList

data class TaskItem(
    val id: Int,
    val name: String,
    val date: String,
    val enabled: Boolean,
    val priority: Int,
    var numberInList: Int = -1
)
