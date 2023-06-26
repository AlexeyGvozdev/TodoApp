package com.sinx.coredbinterface.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskDbModel(

    @PrimaryKey
    val name: String,
    val date: String,
    val enabled: Boolean,
    val priority: Int
)
