package com.example.task.apis

import com.example.task.model.Repo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    @GET
    suspend fun getRepository(@Url url: String): Response<Repo>
}