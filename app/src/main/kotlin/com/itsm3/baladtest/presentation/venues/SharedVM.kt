package com.itsm3.baladtest.presentation.venues

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.itsm3.baladtest.domain.common.ResultState
import com.itsm3.baladtest.domain.entity.VenuesEntity
import com.itsm3.baladtest.domain.usecase.venues.explore.IExploreUseCase
import com.itsm3.baladtest.presentation.base.BaseVM
import javax.inject.Inject

class SharedVM @Inject constructor(
    private val exploreUseCase: IExploreUseCase
) : BaseVM() {
    private var exploreLiveData: MutableLiveData<ResultState<PagedList<VenuesEntity.Explore>>> =
        MutableLiveData()

    fun explore(): Unit {
        exploreUseCase.explore().subscribe() {
            exploreLiveData.postValue(it)
        }?.track()
    }

    fun observe() = exploreLiveData
}