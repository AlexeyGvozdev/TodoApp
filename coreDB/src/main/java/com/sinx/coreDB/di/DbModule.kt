package com.sinx.coreDB.di

import AppScope
import android.content.Context
import com.sinx.coreDB.ToDoAppDatabase
import com.sinx.coredbinterface.dao.TaskDAO
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @Provides
    @AppScope
    fun provideToDoAppDatabase(context: Context) = ToDoAppDatabase.getInstance(context)

    @Provides
    @AppScope
    fun provideTaskDao(context: Context): TaskDAO {
        return provideToDoAppDatabase(context).taskDao()
    }
}
