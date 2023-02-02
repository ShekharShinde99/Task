package com.example.task.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.task.model.Repo

@Database(entities = [Repo::class], version = 1)
abstract class RepoDB : RoomDatabase() {

    abstract fun roomDao(): RoomDao

    companion object {
        private var INSTANCE: RepoDB? = null

        fun getDB(context: Context): RepoDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, RepoDB::class.java, "repoDB").build()
            }
            return INSTANCE!!
        }
    }
}