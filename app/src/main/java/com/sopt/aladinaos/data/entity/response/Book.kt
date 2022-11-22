package com.sopt.aladinaos.data.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: Int,
    val name: String,
    val cover: String,
    val price: Int,
    val point: Int
)
