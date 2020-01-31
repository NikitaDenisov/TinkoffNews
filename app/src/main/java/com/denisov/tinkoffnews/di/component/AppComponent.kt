package com.denisov.tinkoffnews.di.component

import com.denisov.tinkoffnews.data.api.TinkoffApi
import com.denisov.tinkoffnews.di.module.ApiModule
import com.denisov.tinkoffnews.di.scope.PerApplication
import com.denisov.tinkoffnews.presentation.App
import com.denisov.tinkoffnews.presentation.Schedulers
import dagger.Component
import okhttp3.OkHttpClient

@Component(modules = [ApiModule::class])
@PerApplication
interface AppComponent {

    fun getOkHttpClient(): OkHttpClient

    fun getTinkoffApi(): TinkoffApi

    fun getSchedulers(): Schedulers

    fun inject(app: App)
}