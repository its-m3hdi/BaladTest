package com.itsm3.baladtest.data.datasource.explore.remote

import com.itsm3.baladtest.data.api.ExploreService
import com.itsm3.baladtest.data.api.dto.explore.ExploreResponseDto
import com.itsm3.baladtest.data.common.ioScheduler
import com.itsm3.baladtest.domain.entity.VenuesEntity
import io.reactivex.Single
import javax.inject.Inject

class ExploreApiDataSourceImp @Inject constructor(private val service: ExploreService) :
    IExploreApiDataSource {
    override fun explore(
        latLng: String,
        limit: Int,
        radius: Int,
        offset: Int
    ): Single<List<VenuesEntity.Explore>> =
        service.explore(
            latLng.toString(),
            limit, radius, offset
        ).ioScheduler()
            .map { t ->
                t.response.groups.map { it.items.map { it.mapTo() } }.flatten()
            }
}