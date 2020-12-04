package com.itsm3.baladtest.data.datasource.explore.local

import androidx.paging.DataSource
import com.itsm3.baladtest.data.datasource.IBaseDataSource
import com.itsm3.baladtest.domain.entity.VenuesEntity

interface IExploreDbDataSource : IBaseDataSource {
    fun getExploreDbDataSourceFactory(): DataSource.Factory<Int, VenuesEntity.Explore>
    fun persist(exploreList: List<VenuesEntity.Explore>, insertFinished: () -> Unit)
    fun removeAll(finished: () -> Unit)
}