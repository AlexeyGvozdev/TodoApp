package com.sinx.coreDbImpl.di

import android.content.Context
import com.sinx.core.AppScope
import com.sinx.coreDbImpl.ToDoAppDatabase
import com.sinx.coredbinterface.dao.TaskDAO
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @Provides
    @AppScope
    fun provideToDoAppDatabase(context: Context): ToDoAppDatabase = ToDoAppDatabase.getInstance(context)

    @Provides
    @AppScope
    fun provideTaskDao(appDatabase: ToDoAppDatabase): TaskDAO {
        return appDatabase.taskDao()
    }
}