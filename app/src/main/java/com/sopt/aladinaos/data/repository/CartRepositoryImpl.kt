package com.sopt.aladinaos.data.repository

import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.Book
import com.sopt.aladinaos.data.source.remote.RemoteCartDataSource
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val remoteCartDataSource: RemoteCartDataSource
) : CartRepository {
    override suspend fun getBasket(): Result<BaseResponse<List<Book>>> =
        runCatching { remoteCartDataSource.getBasket() }
}
