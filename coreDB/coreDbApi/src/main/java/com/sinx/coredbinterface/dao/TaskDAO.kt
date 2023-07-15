package com.sinx.coredbinterface.dao

import androidx.room.*
import com.sinx.coredbinterface.entity.TaskDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(taskDB: TaskDbModel)

    @Query("SELECT * FROM task ORDER BY numberInList ASC")
    fun getTaskList(): Flow<List<TaskDbModel>>

    @Update
    suspend fun updateTasks(taskList: List<TaskDbModel>)

//    @Query("DELETE FROM task WHERE name")
//    fun deleteTaskFromList (name: String)
}