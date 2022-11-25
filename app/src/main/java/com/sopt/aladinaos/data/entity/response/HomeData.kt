package com.sopt.aladinaos.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeData(
    val pick: List<Pick>,
    val topic: List<Topic>
) {
    @Serializable
    data class Pick(
        @SerialName("id")
        val bookId: Int,
        val name: String,
        val thumbnail: String,
        val cover: String,
        val description: String,
        val summary: String,
        val author: String,
        val painter: String,
        val intro: String,
        val publisher: String,
        val price: Int,
        @SerialName("discount_rate")
        val discountRate: Int,
        val content: String,
        val point: Int,
        val topic: Boolean,
        val pick: Boolean

    )

    @Serializable
    data class Topic(
        @SerialName("id")
        val bookId: Int,
        val name: String,
        val thumbnail: String,
        val cover: String,
        val description: String,
        val summary: String,
        val author: String,
        val painter: String,
        val intro: String,
        val publisher: String,
        val price: Int,
        @SerialName("discount_rate")
        val discountRate: Int,
        val content: String,
        val point: Int,
        val topic: Boolean,
        val pick: Boolean
    )
}
