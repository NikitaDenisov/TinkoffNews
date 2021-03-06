package com.denisov.tinkoffnews.di

import android.app.Application
import com.denisov.tinkoffnews.di.component.AppComponent
import com.denisov.tinkoffnews.di.component.DaggerAppComponent
import com.denisov.tinkoffnews.di.module.ApiModule
import com.denisov.tinkoffnews.di.module.DatabaseModule

object ComponentFactory {

    fun createAppComponent(application: Application): AppComponent {
        return DaggerAppComponent
            .builder()
            .apiModule(ApiModule())
            .databaseModule(DatabaseModule(application))
            .build()
    }
}