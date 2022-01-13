package com.example.myapplication.data.model.response.base

import com.google.gson.annotations.SerializedName

data class ResponseListWrapper<T>(
    @SerializedName("error")
    val error: Boolean = false,

    @SerializedName("results")
    val data: List<T>? = null,

    @SerializedName("message")
    val message: String? = ""
)