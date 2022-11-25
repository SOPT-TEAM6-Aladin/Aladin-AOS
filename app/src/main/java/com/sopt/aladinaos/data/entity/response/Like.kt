package com.sopt.aladinaos.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Like(
    val id: Int,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("book_id")
    val bookId: Int,
    val likeCount: Int,
    val hasLike: Boolean
)
