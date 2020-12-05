package com.itsm3.baladtest.data.repository.explore

import androidx.paging.PagedList
import com.itsm3.baladtest.data.datasource.explore.IPagedListDataSource
import com.itsm3.baladtest.domain.common.ResultState
import com.itsm3.baladtest.domain.entity.VenuesEntity
import io.reactivex.Flowable
import javax.inject.Inject

class ExploreRepoImp @Inject constructor(private val dataSource: IPagedListDataSource) :
    IExploreRepo {
    override fun getExploreVenues(
        latLng: String,
        radius: Int,
        limit: Int
    ): Flowable<ResultState<PagedList<VenuesEntity.Explore>>> {
        return dataSource.getExploreVenues(latLng, radius, limit)
    }

}