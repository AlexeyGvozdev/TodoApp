package com.sinx.coreDB.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project")
data class ProjectDbModel (

    @PrimaryKey
    val nameProject: String,
    val dataProject: String
    )