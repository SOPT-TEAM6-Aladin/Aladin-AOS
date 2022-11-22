package com.sopt.aladinaos.data.entity.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    @SerializedName("data") val `data`: T
)
