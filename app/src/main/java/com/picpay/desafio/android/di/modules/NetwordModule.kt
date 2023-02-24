package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.remote.service.PicPayService
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

private const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
private const val CACHE_SIZE = (10 * 1024 * 1024).toLong()
private const val CACHE_DIR_NAME = "cache-dir"

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PicPayService::class.java)
    }

    single {
        OkHttpClient.Builder()
            .apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                addInterceptor(CacheInterceptor())
                cache(get())
            }.build()
    }

    single {
        Cache(File(androidContext().cacheDir, CACHE_DIR_NAME), CACHE_SIZE)
    }
}