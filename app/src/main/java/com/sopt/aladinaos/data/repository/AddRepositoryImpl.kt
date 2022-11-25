package com.sopt.aladinaos.data.repository

import com.sopt.aladinaos.data.entity.response.Add
import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.RequestAddToCartDto
import com.sopt.aladinaos.data.source.remote.RemoteAddDataSource
import javax.inject.Inject

class AddRepositoryImpl @Inject constructor(
    private val remoteAddDataSource: RemoteAddDataSource
) : AddRepository {
    override suspend fun addToCart(id: RequestAddToCartDto): Result<BaseResponse<Add>> =
        runCatching { remoteAddDataSource.addToCart(id) }
}
