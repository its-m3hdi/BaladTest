package com.itsm3.baladtest.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseVM : ViewModel() {
    private val mDisposable = CompositeDisposable()

    //extension method
    protected fun Disposable.track() {
        mDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }
}