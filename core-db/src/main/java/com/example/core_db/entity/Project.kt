package com.example.core_db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class Project (
    @PrimaryKey(autoGenerate = true)
    val id: Int
)