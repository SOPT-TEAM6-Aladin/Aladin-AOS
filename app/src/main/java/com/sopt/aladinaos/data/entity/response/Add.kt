package com.sopt.aladinaos.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Add(
    val id: Int,
    @SerialName("book_id")
    val bookId: Int,
    @SerialName("user_id")
    val userId: Int
)
