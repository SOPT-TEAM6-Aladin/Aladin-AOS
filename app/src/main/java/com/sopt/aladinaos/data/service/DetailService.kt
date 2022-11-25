package com.sopt.aladinaos.data.service

import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.Detail
import com.sopt.aladinaos.data.entity.response.Like
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

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
}
