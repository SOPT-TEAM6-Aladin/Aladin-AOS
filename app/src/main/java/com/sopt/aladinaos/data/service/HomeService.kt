package com.sopt.aladinaos.data.service

import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.HomeData
import retrofit2.Call
import retrofit2.http.GET

interface HomeService {
    @GET("api/book")
    fun getHomeData(): Call<BaseResponse<HomeData>>
}
