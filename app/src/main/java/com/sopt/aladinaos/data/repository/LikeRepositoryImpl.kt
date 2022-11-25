package com.sopt.aladinaos.data.repository

import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.Like
import com.sopt.aladinaos.data.source.remote.RemoteLikeDataSource
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val remoteLikeDataSource: RemoteLikeDataSource
) : LikeRepository {
    override suspend fun putLike(id: Int): Result<BaseResponse<Like>> =
        kotlin.runCatching { remoteLikeDataSource.putLike(id) }
}
