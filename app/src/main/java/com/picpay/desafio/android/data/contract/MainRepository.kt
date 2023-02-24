package com.picpay.desafio.android.data.contract

import com.picpay.desafio.android.data.model.User
import io.reactivex.Single

interface MainRepository {
    fun getUser(): Single<List<User>>
}