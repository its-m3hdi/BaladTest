package com.itsm3.baladtest.data.repository.explore

import androidx.paging.PagedList
import com.itsm3.baladtest.data.repository.IBaseRepo
import com.itsm3.baladtest.domain.common.ResultState
import com.itsm3.baladtest.domain.entity.VenuesEntity
import io.reactivex.Flowable

interface IExploreRepo : IBaseRepo {
    fun getExploreVenues(radius: Int, limit: Int): Flowable<ResultState<PagedList<VenuesEntity.Explore>>>
}