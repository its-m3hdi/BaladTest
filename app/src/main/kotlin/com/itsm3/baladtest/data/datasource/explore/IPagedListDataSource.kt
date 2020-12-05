package com.itsm3.baladtest.data.datasource.explore

import androidx.paging.PagedList
import com.itsm3.baladtest.data.datasource.IBaseDataSource
import com.itsm3.baladtest.domain.common.ResultState
import com.itsm3.baladtest.domain.entity.VenuesEntity
import io.reactivex.Flowable

interface IPagedListDataSource : IBaseDataSource {
    fun getExploreVenues(latLng :String, radius: Int, limit: Int): Flowable<ResultState<PagedList<VenuesEntity.Explore>>>
}