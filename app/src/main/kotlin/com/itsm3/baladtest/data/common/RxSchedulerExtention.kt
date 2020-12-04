package com.itsm3.baladtest.data.common

import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//Flowable
fun <T> Flowable<T>.ioScheduler() = applyScheduler(Schedulers.io())

private fun <T> Flowable<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

//Single
fun <T> Single<T>.ioScheduler() = applyScheduler(Schedulers.io())

private fun <T> Single<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())