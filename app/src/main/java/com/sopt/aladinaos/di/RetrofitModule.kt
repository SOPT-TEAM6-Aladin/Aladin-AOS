package com.sopt.aladinaos.di

import com.sopt.aladinaos.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val CONTENT_TYPE = "Content-Type"
    private const val APPLICATION_JSON = "application/json"
    private const val AUTHORIZATION = "userId"
    private const val USER_ID = "1"

    @Provides
    @Singleton
    fun providesAladinInterceptor(): Interceptor =
        Interceptor { chain ->
            with(chain) {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .addHeader(AUTHORIZATION, USER_ID)
                        .build()
                )
            }
        }

    @Provides
    @Singleton
    fun providesAladinOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

    @Provides
    @Singleton
    fun providesAladinRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.ALADIN_URI)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//    val homePickService: HomeService by lazy {
//        loginRetrofit.create(HomeService::class.java)
//    }
/*
            private val loginRetrofit by lazy {
                Retrofit.Builder()
                    .baseUrl(AUTH_BASE_URL)
                    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                    .build()
            }

            val authService: AuthService by lazy {
                loginRetrofit.create(AuthService::class.java)
            }
            */
}
