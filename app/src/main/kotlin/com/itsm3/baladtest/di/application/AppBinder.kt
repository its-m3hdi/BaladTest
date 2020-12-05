package com.itsm3.baladtest.di.application

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

@Module
interface AppBinder {
    @Binds
    fun provideExecutor(executorService: ExecutorService): Executor

    @Binds
    fun provideVMFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun provideContext(myApplication: MyApplication): Context
}