package com.itsm3.baladtest.di.fragment

import com.itsm3.baladtest.data.api.ExploreService
import com.itsm3.baladtest.data.datasource.explore.local.ExploreDbDataSourceImp
import com.itsm3.baladtest.data.db.explore.IExploreDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.util.concurrent.Executors

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
}