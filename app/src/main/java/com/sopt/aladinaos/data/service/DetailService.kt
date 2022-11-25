package com.sopt.aladinaos.data.service

import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.Detail
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {
    @GET("api/book/{id}")
    suspend fun getBookDetail(
        @Path("id") id: Int
    ): BaseResponse<Detail>
}
