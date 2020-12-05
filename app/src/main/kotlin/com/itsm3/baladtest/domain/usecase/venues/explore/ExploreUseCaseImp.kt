package com.itsm3.baladtest.domain.usecase.venues.explore

import android.util.Log
import androidx.paging.PagedList
import com.itsm3.baladtest.data.repository.explore.IExploreRepo
import com.itsm3.baladtest.domain.common.ResultState
import com.itsm3.baladtest.domain.entity.VenuesEntity
import com.itsm3.baladtest.domain.usecase.platform.location.LocationProvider
import io.reactivex.Flowable
import java.util.concurrent.Executor
import javax.inject.Inject

class ExploreUseCaseImp @Inject constructor(
    private val repo: IExploreRepo,
    private val executor: Executor,
    private val locationProvider: LocationProvider
) : IExploreUseCase {

    override fun explore(): Flowable<ResultState<PagedList<VenuesEntity.Explore>>> {
        requestLocation()
        return repo.getExploreVenues("", 3000, 30) //init to read from db if available
    }

    private fun requestLocation() {
        executor.execute {
            locationProvider.getLocation(60, 100F) {
                it?.let {
                    repo.getExploreVenues("40.7243,-74.0018", 3000, 30)
                }
            }
        }
    }

    override fun onClear() {
        locationProvider.stopUpdate()
    }
}