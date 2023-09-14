package com.sinx.coredbinterface.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskDbModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val date: String,
    val enabled: Boolean,
    val priority: Int,
    var numberInList: Int
)
