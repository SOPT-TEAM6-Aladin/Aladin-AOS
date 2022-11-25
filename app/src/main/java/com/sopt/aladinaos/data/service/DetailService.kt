package com.sopt.aladinaos.data.service

import com.sopt.aladinaos.data.entity.response.*
import retrofit2.http.* // ktlint-disable no-wildcard-imports

interface DetailService {
    // 책 상세 정보 조회
    @GET("api/book/{id}")
    suspend fun getBookDetail(
        @Path("id") id: Int
    ): BaseResponse<Detail>

    // 상세 페이지 좋아요
    @PUT("api/book/{bookId}/like")
    suspend fun putLike(
        @Path("bookId") bookId: Int
    ): BaseResponse<Like>

    // 장바구니 담기
    @POST("api/basket")
    suspend fun addToCart(
        @Body bookId: RequestAddToCartDto
    ): BaseResponse<Add>
}
