package com.itsm3.baladtest.di.application

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class BindViewModelFactory {
    @Binds
    abstract fun provideVMFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}