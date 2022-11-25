package com.sopt.aladinaos.data.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class HomeData(
    val pick: ArrayList<Pick>,
    val topic: ArrayList<Topic>
) {
    @Serializable
    data class Pick(
        val bookId: Int,
        val name: String,
        val thumbnail: String,
        val author: String,
        val painter: String,
        val publisher: String
    )

    @Serializable
    data class Topic(
        val bookId: Int,
        val name: String,
        val cover: String,
        val intro: String
    )
}

