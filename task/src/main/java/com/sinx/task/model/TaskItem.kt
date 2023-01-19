package com.sinx.task.model

data class TaskItem(
    val id : Int,
    val name : String,
    val date : String,
    val enabled: Boolean,
    val priority : Int
)
