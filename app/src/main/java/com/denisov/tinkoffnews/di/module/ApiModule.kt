package com.denisov.tinkoffnews.di.module

import com.denisov.tinkoffnews.data.converter.AdapterFactory
import com.denisov.tinkoffnews.data.converter.NewsDeserializer
import com.denisov.tinkoffnews.data.dto.News
import com.denisov.tinkoffnews.di.scope.PerApplication
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.util.concurrent.TimeUnit

@Module
class ApiModule {

    @Provides
    @PerApplication
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(Level.BASIC) })
            .build()

    @Provides
    @PerApplication
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapterFactory(AdapterFactory())
        .registerTypeAdapter(News::class.java, NewsDeserializer())
        .create()

    companion object {
        const val TIMEOUT = 30L // 30 seconds
    }
}