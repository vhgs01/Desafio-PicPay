package com.picpay.desafio.android.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            AndroidLogger()
            androidContext(this@MyApplication)

            modules(listOf(mainModule, domainModule, networkModule))
        }
    }
}