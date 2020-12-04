package com.itsm3.baladtest.di.application.component

import com.itsm3.baladtest.di.activity.ActivitiesModule
import com.itsm3.baladtest.di.application.module.AppModule
import com.itsm3.baladtest.di.application.MyApplication
import com.itsm3.baladtest.di.application.BindViewModelFactory
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivitiesModule::class,
        BindViewModelFactory::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<MyApplication>
}