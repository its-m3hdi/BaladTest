package com.itsm3.baladtest.di.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.itsm3.baladtest.di.application.MyApplication
import dagger.Lazy
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.itsm3.baladtest.BuildConfig
import com.itsm3.baladtest.data.api.AuthInterceptor
import dagger.Module
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [BindAuthModule::class])
object NetworkModule {
    @Provides
    @JvmStatic
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.v("Balad-Network", message)
            }
        })

        httpLoggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        return httpLoggingInterceptor
    }

    @Provides
    @JvmStatic
    fun provideOkHttpCache(application: MyApplication): Cache =
        Cache(application.cacheDir, 50000000)

    @Provides
    @JvmStatic
    fun provideClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        cache: Cache
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .cache(cache).build()

    @Provides
    @JvmStatic
    fun provideGson(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    @Provides
    @JvmStatic
    fun provideRetrofit(client: Lazy<OkHttpClient>): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .callFactory(object : Call.Factory {
                override fun newCall(request: Request): Call =
                    client.get().newCall(request)
            })
            .baseUrl(BuildConfig.BASE_URL)
            .build()
}