package com.sinx.coredbinterface.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sinx.coredbinterface.entity.ProjectDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(projectDB: ProjectDbModel)

    @Query("SELECT * FROM project ORDER BY nameProject")
    fun getTaskList(): Flow<List<ProjectDbModel>>
}