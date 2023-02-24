package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.contract.MainRepository
import com.picpay.desafio.android.domain.contract.UserUseCase
import com.picpay.desafio.android.data.remote.repository.MainRepositoryImpl
import com.picpay.desafio.android.domain.usecase.UserUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<UserUseCase> { UserUseCaseImpl(get()) }

    factory<MainRepository> { MainRepositoryImpl(get()) }
}