package com.itsm3.baladtest.di.application.module

import com.itsm3.baladtest.di.network.NetworkModule
import com.itsm3.baladtest.di.database.DbModule
import dagger.Module

@Module(includes = [NetworkModule::class, DbModule::class])
abstract class AppModule