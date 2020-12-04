package com.itsm3.baladtest.data.datasource.explore.remote

import com.itsm3.baladtest.data.datasource.IBaseDataSource
import com.itsm3.baladtest.domain.entity.VenuesEntity
import io.reactivex.Single

interface IExploreApiDataSource : IBaseDataSource {
    fun explore(
        latLng: String,
        limit: Int,
        radius: Int,
        offset: Int
    ): Single<List<VenuesEntity.Explore>>
}