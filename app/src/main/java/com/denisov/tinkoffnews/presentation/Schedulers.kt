package com.denisov.tinkoffnews.presentation

import com.denisov.tinkoffnews.di.scope.PerApplication
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerApplication
class Schedulers @Inject constructor() {

    val io = Schedulers.io()
}