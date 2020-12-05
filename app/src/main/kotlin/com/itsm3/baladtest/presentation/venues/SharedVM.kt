package com.itsm3.baladtest.presentation.venues

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.itsm3.baladtest.domain.common.ResultState
import com.itsm3.baladtest.domain.entity.VenuesEntity
import com.itsm3.baladtest.domain.usecase.venues.explore.IExploreUseCase
import com.itsm3.baladtest.presentation.base.BaseVM
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import javax.inject.Inject

class SharedVM @Inject constructor(
    private val exploreUseCase: IExploreUseCase,
    private val executor: Executor
) : BaseVM() {
    private var exploreLiveData: MutableLiveData<ResultState<PagedList<VenuesEntity.Explore>>> =
        MutableLiveData()

    fun explore() {
        executor.execute {
            onCleared()
            exploreUseCase.explore().subscribe() {
                exploreLiveData.postValue(it)
            }?.track()
        }
    }

    fun observe() = exploreLiveData

    override fun onCleared() {
        super.onCleared()
        exploreUseCase.onClear() // exceptional
    }
}