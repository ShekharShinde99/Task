package com.example.task.repository

import com.example.task.apis.ApiInterface
import com.example.task.room.RepoDB
import java.lang.Exception

class ReposRepository(private val apiInterface: ApiInterface, val db: RepoDB) {

    suspend fun getRepo(url: String): Boolean {
        try {
            val result = apiInterface.getRepository(url)
            if (result != null && result.isSuccessful) {
                db.roomDao().insertRepo(result.body()!!)
                return true
            }
            return false
        } catch (e: Exception) {
            return false
        }
    }

}