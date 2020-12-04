package com.itsm3.baladtest.data.datasource.explore

import androidx.paging.PagedList
import com.itsm3.baladtest.data.datasource.explore.local.IExploreDbDataSource
import com.itsm3.baladtest.data.datasource.explore.remote.IExploreApiDataSource
import com.itsm3.baladtest.domain.entity.VenuesEntity
import javax.inject.Inject

class ExplorePagedListBoundaryCallback @Inject constructor(
    private val apiDataSource: IExploreApiDataSource,
    private val dbDataSource: IExploreDbDataSource,
) :
    PagedList.BoundaryCallback<VenuesEntity.Explore>() {

    init {
        val latLng: String
        val limit: Int
        var radius: Int
    }
}