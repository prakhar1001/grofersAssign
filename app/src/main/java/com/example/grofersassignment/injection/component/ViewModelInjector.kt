package com.example.grofersassignment.injection.component

import com.example.grofersassignment.injection.module.NetworkModule
import com.example.grofersassignment.ui.monument.MonumentListViewModel
import com.example.grofersassignment.ui.monument.MonumentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    fun inject(monumentListViewModel: MonumentListViewModel)
    fun inject(monumentViewModel: MonumentViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}