package com.denisov.tinkoffnews.presentation

import android.app.Application
import com.denisov.tinkoffnews.di.initializeAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeAppComponent(this)
    }
}