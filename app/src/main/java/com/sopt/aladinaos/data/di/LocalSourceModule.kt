package com.sopt.aladinaos.data.di

import com.sopt.aladinaos.data.source.local.LocalDataSource
import com.sopt.aladinaos.data.source.local.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalSourceModule {
    @Singleton
    @Provides
    fun providesLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource =
        localDataSourceImpl
}
