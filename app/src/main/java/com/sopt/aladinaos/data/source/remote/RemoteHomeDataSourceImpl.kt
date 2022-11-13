package com.sopt.aladinaos.data.source.remote

import com.sopt.aladinaos.data.service.HomeService
import javax.inject.Inject

class RemoteHomeDataSourceImpl @Inject constructor(
    homeService: HomeService
) : RemoteHomeDataSource
