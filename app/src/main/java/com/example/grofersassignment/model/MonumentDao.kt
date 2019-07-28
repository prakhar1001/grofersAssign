package com.example.grofersassignment.model

import androidx.room.*

@Dao
interface MonumentDao {
    @get:Query("SELECT * FROM monument")
    val all: List<MonumentEntity>

    @Insert
    fun insertMonument(monumentEntity: MonumentEntity)
}