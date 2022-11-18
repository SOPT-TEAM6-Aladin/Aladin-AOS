package com.sopt.aladinaos.di

import com.sopt.aladinaos.data.source.remote.RemoteCartDataSource
import com.sopt.aladinaos.data.source.remote.RemoteCartDataSourceImpl
import com.sopt.aladinaos.data.source.remote.RemoteDetailDataSource
import com.sopt.aladinaos.data.source.remote.RemoteDetailDataSourceImpl
import com.sopt.aladinaos.data.source.remote.RemoteHomeDataSource
import com.sopt.aladinaos.data.source.remote.RemoteHomeDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteSourceModule {
    @Provides
    @Singleton
    fun providesRemoteHomeSource(
        remoteHomeDataSourceImpl: RemoteHomeDataSourceImpl
    ): RemoteHomeDataSource = remoteHomeDataSourceImpl

    @Provides
    @Singleton
    fun providesRemoteDetailSource(
        detailDataSourceImpl: RemoteDetailDataSourceImpl
    ): RemoteDetailDataSource = detailDataSourceImpl

    @Provides
    @Singleton
    fun providesRemoteCartSource(
        cartDataSourceImpl: RemoteCartDataSourceImpl
    ): RemoteCartDataSource = cartDataSourceImpl
}
