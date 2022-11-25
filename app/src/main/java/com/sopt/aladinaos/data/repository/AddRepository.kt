package com.sopt.aladinaos.data.repository

import com.sopt.aladinaos.data.entity.response.Add
import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.RequestAddToCartDto

interface AddRepository {
    suspend fun addToCart(id: RequestAddToCartDto): Result<BaseResponse<Add>>
}
