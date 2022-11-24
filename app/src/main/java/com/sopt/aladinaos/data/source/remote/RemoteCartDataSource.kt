package com.sopt.aladinaos.data.source.remote

import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.Book
import com.sopt.aladinaos.data.service.CartService
import javax.inject.Inject

class RemoteCartDataSource @Inject constructor(
    private val cartService: CartService
) {
    suspend fun getBasket(): BaseResponse<List<Book>> =
        cartService.getBasket()
}
