package com.sopt.aladinaos.data.di

import com.sopt.aladinaos.data.service.CartService
import com.sopt.aladinaos.data.service.DetailService
import com.sopt.aladinaos.data.service.HomeService
import com.sopt.aladinaos.data.source.local.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    @Singleton
    fun providesAladinInterceptor(
        localDataSource: LocalDataSource
    ): Interceptor =
        Interceptor { chain ->
            with(chain) {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader(
                            HEADER_AUTHORIZATION,
                            DUMMY_ACCESS_TOKEN
                        )
                        .addHeader(AUTHORIZATION, USER_ID)
                        .build()
                )
            }
        }

    @Provides
    @Singleton
    fun providesHousOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun providesHousRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(ALADIN_URI)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideHomeService(retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)

    @Provides
    fun provideDetailService(retrofit: Retrofit): DetailService =
        retrofit.create(DetailService::class.java)

    @Provides
    fun provideCartService(retrofit: Retrofit): CartService =
        retrofit.create(CartService::class.java)

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val AUTHORIZATION = "Authorization"
        private const val USER_ID = "1"
        private const val ALADIN_URI = "aladin uri"
        private const val DUMMY_ACCESS_TOKEN = "dummy access token"
    }
}
