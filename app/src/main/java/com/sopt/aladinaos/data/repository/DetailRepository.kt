package com.sopt.aladinaos.data.repository

import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.Detail

interface DetailRepository {
    suspend fun getBookDetail(id: Int): Result<BaseResponse<Detail>>
}
