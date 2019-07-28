package com.example.grofersassignment.network

import com.example.grofersassignment.model.MonumentEntity
import io.reactivex.Observable
import retrofit2.http.GET

interface MonumentApi{

    @GET("5d3189cf33000074007ba233")
    fun getMonuments(): Observable<List<MonumentEntity>>
}