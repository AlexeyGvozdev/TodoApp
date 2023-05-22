package com.sinx.coreDB.di

import android.content.Context
import com.sinx.coreDB.ToDoAppDatabase
import com.sinx.coredbinterface.dao.TaskDAO
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @Provides
    fun provideToDoAppDatabase(context: Context) = ToDoAppDatabase.getInstance(context)

    @Provides
    fun provideTaskDao(context: Context): TaskDAO {
        return provideToDoAppDatabase(context).taskDao()
    }
}