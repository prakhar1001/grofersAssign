package com.example.grofersassignment.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.grofersassignment.model.MonumentDao
import com.example.grofersassignment.model.MonumentEntity


@Database(entities = [MonumentEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): MonumentDao
}