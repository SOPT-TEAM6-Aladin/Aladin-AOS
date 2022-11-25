package com.sopt.aladinaos.data.repository

import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.Like

interface LikeRepository {
    suspend fun putLike(id: Int): Result<BaseResponse<Like>>
}
