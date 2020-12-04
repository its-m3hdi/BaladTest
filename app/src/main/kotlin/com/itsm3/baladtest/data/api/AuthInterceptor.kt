package com.itsm3.baladtest.data.api

import com.itsm3.baladtest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var originalReq = chain.request()
        var originalUrl = originalReq.url

        var newUrl = originalUrl.newBuilder()
            .addQueryParameter(BuildConfig.Client_Id_Key, BuildConfig.Client_Id_Value)
            .addQueryParameter(BuildConfig.Client_Secret_Key, BuildConfig.Client_Secret_Value)
            .addQueryParameter(BuildConfig.API_VERSION_Key, BuildConfig.API_VERSION_Value)
            .build()

        var newReq = originalReq.newBuilder().url(newUrl)

        val request: Request = newReq.build()
        return chain.proceed(request)
    }
}