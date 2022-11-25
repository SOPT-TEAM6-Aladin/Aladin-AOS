package com.sopt.aladinaos.data.repository

import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.Book
import com.sopt.aladinaos.data.entity.response.CartResponse

interface CartRepository {
    suspend fun getBasket(): Result<BaseResponse<List<CartResponse>>>
}
