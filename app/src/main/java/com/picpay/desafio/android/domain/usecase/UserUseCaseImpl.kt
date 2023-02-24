package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.contract.MainRepository
import com.picpay.desafio.android.domain.contract.UserUseCase
import io.reactivex.Single
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val repository: MainRepository
) : UserUseCase {

    override fun getUsers(): Single<List<User>> {
        return repository.getUser()
    }

}