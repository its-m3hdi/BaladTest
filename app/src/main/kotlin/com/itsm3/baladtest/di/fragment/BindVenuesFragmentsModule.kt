package com.itsm3.baladtest.di.fragment

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.itsm3.baladtest.data.datasource.explore.ExplorePagedListBoundaryCallback
import com.itsm3.baladtest.data.datasource.explore.ExplorePagedListDataSource
import com.itsm3.baladtest.data.datasource.explore.IPagedListDataSource
import com.itsm3.baladtest.data.datasource.explore.local.ExploreDbDataSourceImp
import com.itsm3.baladtest.data.datasource.explore.local.IExploreDbDataSource
import com.itsm3.baladtest.data.datasource.explore.remote.ExploreApiDataSourceImp
import com.itsm3.baladtest.data.datasource.explore.remote.IExploreApiDataSource
import com.itsm3.baladtest.data.repository.explore.ExploreRepoImp
import com.itsm3.baladtest.data.repository.explore.IExploreRepo
import com.itsm3.baladtest.di.application.ViewModelKey
import com.itsm3.baladtest.domain.entity.VenuesEntity
import com.itsm3.baladtest.domain.usecase.venues.explore.ExploreUseCaseImp
import com.itsm3.baladtest.domain.usecase.venues.explore.IExploreUseCase
import com.itsm3.baladtest.presentation.venues.SharedVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import javax.inject.Named

@Module
interface BindVenuesFragmentsModule {
    @VenuesScope
    @Binds
    @IntoMap
    @ViewModelKey(SharedVM::class)
    fun provideShareVM(sharedVM: SharedVM): ViewModel

    @Binds
    fun provideExploreRepository(exploreRepo: ExploreRepoImp): IExploreRepo

    @Binds
    fun provideExploreApiDataSource(exploreApiDataSource: ExploreApiDataSourceImp): IExploreApiDataSource

    @VenuesScope
    @Binds
    fun provideExploreDbDataSource(exploreDbDataSource: ExploreDbDataSourceImp): IExploreDbDataSource

    @Binds
    fun provideExploreUseCaseImp(exploreUseCase: ExploreUseCaseImp): IExploreUseCase

    @Binds
    fun provideIPagedListDataSourceImp(explorePagedListDataSource: ExplorePagedListDataSource): IPagedListDataSource

}