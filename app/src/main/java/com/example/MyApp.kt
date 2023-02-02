package com.example.task

import android.app.Application
import com.example.task.apis.ApiInterface
import com.example.task.apis.ApiUtils
import com.example.task.repository.ReposRepository
import com.example.task.room.RepoDB

class MyApp: Application() {
    lateinit var repository: ReposRepository
    override fun onCreate() {
        super.onCreate()
        val apiInterface =  ApiUtils.instance().create(ApiInterface::class.java)
        val database = RepoDB.getDB(applicationContext)
        repository = ReposRepository(apiInterface,database)
    }
}