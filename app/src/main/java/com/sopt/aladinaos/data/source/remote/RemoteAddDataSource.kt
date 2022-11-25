package com.sopt.aladinaos.data.source.remote

import com.sopt.aladinaos.data.entity.response.Add
import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.RequestAddToCartDto
import com.sopt.aladinaos.data.service.DetailService
import javax.inject.Inject

class RemoteAddDataSource @Inject constructor(
    private val detailService: DetailService
) {
    suspend fun addToCart(id: RequestAddToCartDto): BaseResponse<Add> =
        detailService.addToCart(id)
}
