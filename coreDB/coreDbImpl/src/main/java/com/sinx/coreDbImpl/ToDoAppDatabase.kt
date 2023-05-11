package com.sinx.coreDbImpl

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sinx.coredbinterface.dao.ProjectDAO
import com.sinx.coredbinterface.dao.TaskDAO
import com.sinx.coredbinterface.entity.ProjectDbModel
import com.sinx.coredbinterface.entity.TaskDbModel

@Database(entities = [TaskDbModel::class, ProjectDbModel::class], version = 1, exportSchema = false)
abstract class ToDoAppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDAO

    abstract fun projectDao(): ProjectDAO

    companion object {
        private var db: ToDoAppDatabase? = null

        private const val DB_NAME = "todo.db"

        private var LOCK = Any()

        fun getInstance(context: Context): ToDoAppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    ToDoAppDatabase::class.java,
                    DB_NAME
                )
                    .build()
                db = instance
                return instance
            }
        }
    }
}