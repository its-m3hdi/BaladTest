package com.itsm3.baladtest.di.application.module

import com.itsm3.baladtest.di.network.NetworkModule
import com.itsm3.baladtest.di.database.DbModule
import com.itsm3.baladtest.di.fragment.VenuesScope
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DbModule::class])
object AppModule {
    @Singleton
    @Provides
    fun provideExecutorService() =
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1)
}