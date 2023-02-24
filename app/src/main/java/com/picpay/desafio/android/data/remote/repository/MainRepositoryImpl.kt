package com.picpay.desafio.android.data.remote.repository

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.contract.MainRepository
import com.picpay.desafio.android.data.remote.service.PicPayService
import io.reactivex.Single
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val service: PicPayService
) : MainRepository {

    override fun getUser(): Single<List<User>> {
        return service.getUsers()
    }

}