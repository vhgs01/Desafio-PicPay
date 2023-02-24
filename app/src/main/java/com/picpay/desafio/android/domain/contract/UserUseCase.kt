package com.picpay.desafio.android.domain.contract

import com.picpay.desafio.android.data.model.User
import io.reactivex.Single

interface UserUseCase {
    fun getUsers(): Single<List<User>>
}