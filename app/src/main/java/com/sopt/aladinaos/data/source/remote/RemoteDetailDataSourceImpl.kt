package com.sopt.aladinaos.data.source.remote

import com.sopt.aladinaos.data.service.DetailService
import javax.inject.Inject

class RemoteDetailDataSourceImpl @Inject constructor(
    detailService: DetailService
) : RemoteDetailDataSource
