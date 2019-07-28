package com.example.grofersassignment.base

import androidx.lifecycle.ViewModel
import com.example.grofersassignment.injection.component.DaggerViewModelInjector
import com.example.grofersassignment.injection.component.ViewModelInjector
import com.example.grofersassignment.injection.module.NetworkModule
import com.example.grofersassignment.ui.monument.MonumentListViewModel
import com.example.grofersassignment.ui.monument.MonumentViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MonumentListViewModel -> injector.inject(this)
            is MonumentViewModel -> injector.inject(this)
        }
    }
}