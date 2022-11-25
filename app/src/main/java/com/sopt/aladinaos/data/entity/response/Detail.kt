package com.sopt.aladinaos.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Detail(
    val id: Int,
    val name: String,
    val intro: String,
    val author: String,
    val painter: String,
    val like: Boolean,
    var likeCount: Int,
    val price: Int,
    @SerialName("discount_rate")
    val discountRate: Int,
    val description: String,
    val content: String,
    val summary: String,

    val thumbnail: String,
    val cover: String,
    val point: Int,
    val topic: Boolean,
    val pick: Boolean
)
