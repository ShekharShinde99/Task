package com.example.task.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class Repo(
    val name: String,
    val html_url: String,
    val description: String,
    @PrimaryKey val id: String
)
