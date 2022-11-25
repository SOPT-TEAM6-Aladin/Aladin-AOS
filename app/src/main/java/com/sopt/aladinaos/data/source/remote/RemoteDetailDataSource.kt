package com.sopt.aladinaos.data.source.remote

import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.Detail
import com.sopt.aladinaos.data.service.DetailService
import javax.inject.Inject

class RemoteDetailDataSource @Inject constructor(
    private val detailService: DetailService
) {
    suspend fun getBookDetail(id: Int): BaseResponse<Detail> =
        detailService.getBookDetail(id)
}
