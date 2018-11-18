package com.dahyeon.mirim.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Connector {
    var api: Api

    private const val url = "http://mirim-app-backend.herokuapp.com/api/v1/"

    init {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(logger).build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }
}
