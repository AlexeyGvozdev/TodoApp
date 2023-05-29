package com.sinx.taskList

data class TaskItem(
    val name: String,
    val date: String,
    var enabled: Boolean,
    val priority: Int
)
