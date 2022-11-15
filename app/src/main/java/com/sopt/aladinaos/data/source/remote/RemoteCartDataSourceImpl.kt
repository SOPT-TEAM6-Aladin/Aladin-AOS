package com.sopt.aladinaos.data.source.remote

import com.sopt.aladinaos.data.service.CartService
import javax.inject.Inject

class RemoteCartDataSourceImpl @Inject constructor(
    cartService: CartService
) : RemoteCartDataSource
