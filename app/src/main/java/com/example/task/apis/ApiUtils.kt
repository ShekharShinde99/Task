package com.example.task.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtils {
    val BASE_URL = "https://api.github.com/"

    fun instance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
}