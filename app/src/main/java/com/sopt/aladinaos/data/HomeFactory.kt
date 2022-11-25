package com.sopt.aladinaos.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.aladinaos.BuildConfig
import com.sopt.aladinaos.data.service.HomeService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object HomeFactory {
    private const val APPLICATION_JSON = "application/json"

    @OptIn(ExperimentalSerializationApi::class)
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.ALADIN_URI)
            .addConverterFactory(Json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ServicePool {
    val homeService = HomeFactory.create<HomeService>()
}
