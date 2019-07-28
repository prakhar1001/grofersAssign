package com.example.grofersassignment.ui.monument

import androidx.lifecycle.MutableLiveData
import com.example.grofersassignment.base.BaseViewModel
import com.example.grofersassignment.model.MonumentEntity


class MonumentViewModel: BaseViewModel() {

    private val monumentName = MutableLiveData<String>()
    private val monumentPlace = MutableLiveData<String>()
    private val monumentUrl = MutableLiveData<String>()

    fun bind(monumentEntity: MonumentEntity){
        monumentName.value = monumentEntity.name
        monumentPlace.value = monumentEntity.place
        monumentUrl.value = monumentEntity.url
    }

    fun getMonumentName():MutableLiveData<String>{
        return monumentName
    }

    fun getMonumentPlace():MutableLiveData<String>{
        return monumentPlace
    }

    fun getMonumentUrl():MutableLiveData<String>{
        return monumentUrl
    }
}