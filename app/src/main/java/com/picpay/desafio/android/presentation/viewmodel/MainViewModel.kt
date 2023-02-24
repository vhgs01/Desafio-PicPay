package com.picpay.desafio.android.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.domain.contract.UserUseCase
import com.picpay.desafio.android.util.defaultSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    val successLiveData = MutableLiveData<List<User>>()
    val failureLiveData = MutableLiveData<Boolean>()

    private val composeDisposable = CompositeDisposable()

    fun getUsers() {
        userUseCase.getUsers()
            .defaultSchedulers()
            .subscribe({
                successLiveData.postValue(it)
            }, {
                failureLiveData.postValue(true)
            })
            .also { composeDisposable.add(it) }
    }

    override fun onCleared() {
        super.onCleared()

        composeDisposable.dispose()
    }

}