package com.example.task.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.task.model.Repo

@Dao
interface RoomDao {
    @Insert
    suspend fun insertRepo(repo: Repo)

    @Query("SELECT * FROM repos")
    fun getRepos(): LiveData<List<Repo>>

}