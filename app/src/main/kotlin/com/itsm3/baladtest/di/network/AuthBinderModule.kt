package com.itsm3.baladtest.di.network

import com.itsm3.baladtest.data.api.AuthInterceptor
import dagger.Binds
import dagger.Module
import okhttp3.Interceptor

@Module
abstract class AuthBinderModule {
    @Binds
    abstract fun bindAuthInterceptor(authInterceptor: AuthInterceptor): Interceptor
}