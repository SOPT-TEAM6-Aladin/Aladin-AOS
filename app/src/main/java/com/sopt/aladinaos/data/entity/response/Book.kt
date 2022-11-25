package com.sopt.aladinaos.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: Int = -1,
    val name: String = "",
    val cover: String = "",
    val painter: String = "",
    val price: Int = -1,
    @SerialName("discount_rate")
    val discountRate: Int = -1,
    val point: Int = -1
)
