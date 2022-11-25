package com.sopt.aladinaos.data.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class RequestAddToCartDto(
    val bookId: Int
)
