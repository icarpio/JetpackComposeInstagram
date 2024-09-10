package com.example.jetpackcomposeinstagram.network

import RandomUserResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RandomUserApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(RandomUserService::class.java)
}

interface RandomUserService {
    @GET(".")
    suspend fun getRandomUsers(@Query("results") results: Int = 10): Response<RandomUserResponse>
}