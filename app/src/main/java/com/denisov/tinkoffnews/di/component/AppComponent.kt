package com.denisov.tinkoffnews.di.component

import com.denisov.tinkoffnews.data.api.TinkoffApi
import com.denisov.tinkoffnews.data.database.NewsDao
import com.denisov.tinkoffnews.di.module.ApiModule
import com.denisov.tinkoffnews.di.module.DatabaseModule
import com.denisov.tinkoffnews.di.scope.PerApplication
import com.denisov.tinkoffnews.presentation.App
import com.denisov.tinkoffnews.presentation.Schedulers
import dagger.Component

@Component(modules = [ApiModule::class, DatabaseModule::class])
@PerApplication
interface AppComponent {

    fun getTinkoffApi(): TinkoffApi

    fun getSchedulers(): Schedulers

    fun getNewDao(): NewsDao

    fun inject(app: App)
}