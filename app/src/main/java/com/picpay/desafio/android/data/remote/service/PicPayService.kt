package com.picpay.desafio.android.data.remote.service

import com.picpay.desafio.android.data.model.User
import io.reactivex.Single
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Single<List<User>>

}