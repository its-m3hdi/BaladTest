package com.itsm3.baladtest.domain.usecase.venues.explore

import androidx.paging.PagedList
import com.itsm3.baladtest.domain.common.ResultState
import com.itsm3.baladtest.domain.entity.VenuesEntity
import com.itsm3.baladtest.domain.usecase.IBaseUseCase
import io.reactivex.Flowable

interface IExploreUseCase : IBaseUseCase {
    fun explore(): Flowable<ResultState<PagedList<VenuesEntity.Explore>>>
}