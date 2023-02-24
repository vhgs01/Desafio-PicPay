package com.picpay.desafio.android.util

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

fun <T> Single<T>.defaultSchedulers() = this.observeOn(AndroidSchedulers.mainThread())