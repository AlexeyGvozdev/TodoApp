package com.example.core_db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.core_db.dao.ProjectDao
import com.example.core_db.dao.TaskDao
import com.example.core_db.entity.Project
import com.example.core_db.entity.Task

@Database(entities = [Task::class, Project::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "todo.db"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
    abstract fun taskDao(): TaskDao
    abstract fun projectDao(): ProjectDao
}