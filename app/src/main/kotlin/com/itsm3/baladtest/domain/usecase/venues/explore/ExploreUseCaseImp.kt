package com.itsm3.baladtest.domain.usecase.venues.explore

import androidx.paging.PagedList
import com.itsm3.baladtest.data.repository.explore.IExploreRepo
import com.itsm3.baladtest.domain.common.ResultState
import com.itsm3.baladtest.domain.entity.VenuesEntity
import io.reactivex.Flowable
import javax.inject.Inject

class ExploreUseCaseImp @Inject constructor(
    private val repo: IExploreRepo
) : IExploreUseCase {

    override fun explore(): Flowable<ResultState<PagedList<VenuesEntity.Explore>>> {
        return repo.getExploreVenues(300, 30)
    }
}