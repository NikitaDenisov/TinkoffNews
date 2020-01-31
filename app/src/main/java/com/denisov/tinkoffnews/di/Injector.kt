package com.denisov.tinkoffnews.di

import android.app.Application
import com.denisov.tinkoffnews.di.component.AppComponent

private lateinit var appComponent: AppComponent

var initializerAppComponent: (Application) -> AppComponent = { ComponentFactory.createAppComponent(it) }

fun initializeAppComponent(application: Application) {
    appComponent = initializerAppComponent(application)
}

fun getAppComponent(): AppComponent {
    return appComponent
}
