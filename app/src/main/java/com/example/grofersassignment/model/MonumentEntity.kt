package com.example.grofersassignment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monument")
data class MonumentEntity(
    val name: String,
    val place: String,
    val url: String
){
    @ColumnInfo(name = "monument_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}