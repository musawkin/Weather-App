package com.example.weather

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MainApi {

    @GET("v1/forecast.json")
    suspend fun response(
        @Query("key") key: String,
        @Query("q") city: String,
        @Query("days") days: Int
    ): Response<ResponseModel>
}