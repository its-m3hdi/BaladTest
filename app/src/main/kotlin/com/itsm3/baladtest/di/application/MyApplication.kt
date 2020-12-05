package com.itsm3.baladtest.di.application

import com.itsm3.baladtest.di.application.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MyApplication : DaggerApplication() {

    companion object{
        lateinit var m: MyApplication
    }
    override fun onCreate() {
        super.onCreate()
        m = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

}