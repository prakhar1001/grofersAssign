package com.example.grofersassignment.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.grofersassignment.model.database.AppDatabase
import com.example.grofersassignment.ui.monument.MonumentListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MonumentListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext,
                AppDatabase::class.java, "monument").build()
            @Suppress("UNCHECKED_CAST")
            return MonumentListViewModel(db.postDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}