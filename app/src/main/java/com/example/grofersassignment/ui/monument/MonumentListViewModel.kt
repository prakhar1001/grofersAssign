package com.example.grofersassignment.ui.monument

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.grofersassignment.R
import com.example.grofersassignment.base.BaseViewModel
import com.example.grofersassignment.model.MonumentDao
import com.example.grofersassignment.model.MonumentEntity
import com.example.grofersassignment.network.MonumentApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MonumentListViewModel(private val monumentDao: MonumentDao) : BaseViewModel() {
    @Inject
    lateinit var monumentApi: MonumentApi

    val monumentListAdapter: MonumentListAdapter = MonumentListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadMonuments() }

    private lateinit var subscription: Disposable

    init {
        loadMonuments()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadMonuments() {
        subscription = Observable.fromCallable { monumentDao.all }
            .concatMap { dbMonumentList ->
                if (dbMonumentList.isEmpty())
                    monumentApi.getMonuments().concatMap {
                            apiMonumentList -> updateValuesInDb(apiMonumentList)

                        Observable.just(apiMonumentList)
                    }
                else
                    Observable.just(dbMonumentList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveMonumentListStart() }
            .doOnTerminate { onRetrieveMonumentListFinish() }
            .subscribe(
                { result -> onRetrieveMonumentListSuccess(result) },
                { onRetrieveMonumentListError() }
            )
    }

    private fun updateValuesInDb(apiMonumentList: List<MonumentEntity>) {
        for (monument in apiMonumentList) {
            monumentDao.insertMonument(monument)
        }

    }

    private fun onRetrieveMonumentListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveMonumentListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveMonumentListSuccess(monumentList: List<MonumentEntity>) {
        monumentListAdapter.updateMonumentList(monumentList)
    }

    private fun onRetrieveMonumentListError() {
        errorMessage.value = R.string.monument_error
    }
}