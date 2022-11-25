package com.sopt.aladinaos.data.service

import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.CartResponse
import retrofit2.http.GET

interface CartService {
    @GET("api/basket")
    suspend fun getBasket(): BaseResponse<List<CartResponse>>
}
