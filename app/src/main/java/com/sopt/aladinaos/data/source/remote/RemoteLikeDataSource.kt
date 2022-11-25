package com.sopt.aladinaos.data.source.remote

import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.Like
import com.sopt.aladinaos.data.service.DetailService
import javax.inject.Inject

class RemoteLikeDataSource @Inject constructor(
    private val detailService: DetailService
) {
    suspend fun putLike(id: Int): BaseResponse<Like> =
        detailService.putLike(id)
}
