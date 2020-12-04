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

class ExplorePagedListDataSource<T> @Inject constructor(
    val pagedListCallback: PagedList.BoundaryCallback<VenuesEntity.Explore>,
    val dbSource: IExploreDbDataSource
) : IPagedListDataSource {

    override fun getExploreVenues(
        radius: Int,
        limit: Int
    ): Flowable<ResultState<PagedList<VenuesEntity.Explore>>> {
        val data = RxPagedListBuilder(
            dbSource.getExploreDbDataSourceFactory(),
            PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .build()
        )
            .setBoundaryCallback(pagedListCallback)
            .buildFlowable(BackpressureStrategy.BUFFER)

        return data.ioScheduler()
            .map { it ->
                if (it.size > 0)
                    ResultState.Success(it)
                else
                    ResultState.Loading(it)
            }
            .onErrorReturn { e -> ResultState.Fail(e, null) }
    }

}