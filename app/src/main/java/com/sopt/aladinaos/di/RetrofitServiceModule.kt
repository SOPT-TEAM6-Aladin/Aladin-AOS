package com.sopt.aladinaos.di

import com.sopt.aladinaos.data.service.CartService
import com.sopt.aladinaos.data.service.DetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {
    @Provides
    fun providesCartService(retrofit: Retrofit): CartService =
        retrofit.create(CartService::class.java)

    @Provides
    fun providesDetailService(retrofit: Retrofit): DetailService =
        retrofit.create(DetailService::class.java)
}
