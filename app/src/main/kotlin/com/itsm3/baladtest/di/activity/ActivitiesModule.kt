package com.itsm3.baladtest.di.activity

import com.itsm3.baladtest.di.fragment.VenuesFragmentsBindingModule
import com.itsm3.baladtest.presentation.venues.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector(modules = [VenuesFragmentsBindingModule::class])
    abstract fun contributeMainActivity(): MainActivity
}