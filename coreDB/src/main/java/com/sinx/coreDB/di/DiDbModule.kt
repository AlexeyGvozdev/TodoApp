package com.sinx.coreDB.di

import dagger.Module
import dagger.Provides

@Module
class DiDbModule {
    @Provides
    fun provideDbModule() = DbModule()
}