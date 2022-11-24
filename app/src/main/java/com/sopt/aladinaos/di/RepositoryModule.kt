package com.sopt.aladinaos.di

import com.sopt.aladinaos.data.repository.CartRepository
import com.sopt.aladinaos.data.repository.CartRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): CartRepository = cartRepositoryImpl
}
