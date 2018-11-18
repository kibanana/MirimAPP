package com.mirimapp.mirim.models

import com.google.gson.annotations.SerializedName

data class AuthModel(
    @SerializedName("accessToken") val token: String,
    @SerializedName("refreshToken") val refreshToken: String? // refresh token은 GET /refresh에서 없음
)
