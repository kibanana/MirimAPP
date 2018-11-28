package com.mirimapp.mirim.models

import com.google.gson.annotations.SerializedName

data class NoticeModel(
    @SerializedName("id") val id: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("ownerEmail") val ownerEmail: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String
)
