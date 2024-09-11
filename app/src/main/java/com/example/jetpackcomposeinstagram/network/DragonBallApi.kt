package com.example.jetpackcomposeinstagram.network

import com.example.jetpackcomposeinstagram.model.DragonBall
import com.example.jetpackcomposeinstagram.model.Item
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object DragonBallApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dragonball-api.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(DragonBallService::class.java)
}

interface DragonBallService {
    @GET("characters")
    suspend fun getCharacters(@Query("page") page: Int = 1, @Query("limit") limit: Int = 58): Response<DragonBall>

    @GET("characters/{characterId}")
    suspend fun getCharacter(@Path("characterId") characterId: String): Response<Item>
}

