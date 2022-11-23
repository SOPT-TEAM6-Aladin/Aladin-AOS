package com.sopt.aladinaos.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: Int,
    val name: String,
    val cover: String,
    val price: Int,
    @SerialName("discount_rate")
    val discountRate: Int,
    val point: Int,
    // response 이외 데이터
    val count: Int = 1
)
