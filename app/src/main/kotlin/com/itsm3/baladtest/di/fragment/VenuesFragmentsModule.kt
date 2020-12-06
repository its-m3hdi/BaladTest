package com.itsm3.baladtest.di.fragment

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.itsm3.baladtest.data.api.ExploreService
import com.itsm3.baladtest.data.datasource.explore.local.ExploreDbDataSourceImp
import com.itsm3.baladtest.data.datasource.explore.local.IExploreDbDataSource
import com.itsm3.baladtest.data.db.explore.IExploreDao
import com.itsm3.baladtest.domain.common.ResultState
import com.itsm3.baladtest.domain.entity.VenuesEntity
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import retrofit2.Retrofit
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Named

@Module
object VenuesFragmentsModule {
    @VenuesScope
    @Provides
    @JvmStatic
    fun provideExploreService(
        retrofit: Retrofit
    ): ExploreService = retrofit.create(ExploreService::class.java)

    @VenuesScope
    @Provides
    @JvmStatic
    fun provideExploreDbDataSource(exploreDao: IExploreDao): ExploreDbDataSourceImp =
        ExploreDbDataSourceImp(exploreDao, Executors.newSingleThreadExecutor())

    @VenuesScope
    @Provides
    @JvmStatic
    @Named("ExplorePagedListBuilder")
    fun provideRxPagedListBuilder(
        dbSource: IExploreDbDataSource
    ): RxPagedListBuilder<Int, VenuesEntity.Explore> =
        RxPagedListBuilder(
            dbSource.getExploreDbDataSourceFactory(),
            PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .build()
        )

    @VenuesScope
    @Provides
    @JvmStatic
    fun providePagedListCallbackPublishSubject() =
        PublishSubject.create<ResultState<PagedList<VenuesEntity.Explore>>>()
}