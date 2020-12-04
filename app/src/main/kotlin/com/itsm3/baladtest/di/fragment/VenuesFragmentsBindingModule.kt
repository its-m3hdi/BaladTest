package com.itsm3.baladtest.di.fragment

import com.itsm3.baladtest.presentation.venues.detail.ExploreDetailFragment
import com.itsm3.baladtest.presentation.venues.explore.ExploreFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [BindVenuesFragmentsModule::class, VenuesFragmentsModule::class])
abstract class VenuesFragmentsBindingModule {
    @ContributesAndroidInjector()
    abstract fun contributeExploreFragment(): ExploreFragment

    @ContributesAndroidInjector()
    abstract fun contributeExploreDetailFragment(): ExploreDetailFragment
}