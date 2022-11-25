package com.sopt.aladinaos.data.repository

import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.Detail
import com.sopt.aladinaos.data.source.remote.RemoteDetailDataSource
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val remoteDetailDataSource: RemoteDetailDataSource
) : DetailRepository {
    override suspend fun getBookDetail(id: Int): Result<BaseResponse<Detail>> =
        kotlin.runCatching { remoteDetailDataSource.getBookDetail(id) }
}
