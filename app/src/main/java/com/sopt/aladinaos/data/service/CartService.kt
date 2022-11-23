package com.sopt.aladinaos.data.service

import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.Book
import retrofit2.http.GET

interface CartService {
    @GET("api/basket")
    suspend fun getBasket(): BaseResponse<List<Book>>
}
