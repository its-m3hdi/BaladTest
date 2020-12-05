package com.itsm3.baladtest.data.datasource.explore

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.itsm3.baladtest.data.common.ioScheduler
import com.itsm3.baladtest.data.datasource.explore.local.IExploreDbDataSource
import com.itsm3.baladtest.domain.common.ResultState
import com.itsm3.baladtest.domain.entity.VenuesEntity
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Named

class ExplorePagedListDataSource @Inject constructor(
    private val pagedListCallback: ExplorePagedListBoundaryCallback,
    @Named("ExplorePagedListBuilder")
    private val rxPagedListBuilder: RxPagedListBuilder<Int, VenuesEntity.Explore>
) : IPagedListDataSource {

    val data = rxPagedListBuilder
        .setBoundaryCallback(pagedListCallback)
        .buildFlowable(BackpressureStrategy.BUFFER)
        .ioScheduler()
        .map { it ->
            if (it.size > 0) ResultState.Success(it)
            else ResultState.Loading(it)
        }.onErrorReturn { e -> ResultState.Fail(e, null) }

    override fun getExploreVenues(
        latLng: String,
        radius: Int,
        limit: Int
    ): Flowable<ResultState<PagedList<VenuesEntity.Explore>>> {
        pagedListCallback.requestNewData(latLng, radius, limit)
        return data
    }

}