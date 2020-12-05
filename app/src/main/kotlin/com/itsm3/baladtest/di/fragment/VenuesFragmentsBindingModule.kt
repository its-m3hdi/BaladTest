package com.itsm3.baladtest.di.fragment

import com.itsm3.baladtest.presentation.venues.detail.ExploreDetailFragment
import com.itsm3.baladtest.presentation.venues.explore.ExploreFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module//(includes = [BindVenuesFragmentsModule::class, VenuesFragmentsModule::class])
abstract class VenuesFragmentsBindingModule {
    @VenuesScope
    @ContributesAndroidInjector(modules = [BindVenuesFragmentsModule::class, VenuesFragmentsModule::class])
    abstract fun contributeExploreFragment(): ExploreFragment

    @VenuesScope
    @ContributesAndroidInjector(modules = [BindVenuesFragmentsModule::class, VenuesFragmentsModule::class])
    abstract fun contributeExploreDetailFragment(): ExploreDetailFragment
}