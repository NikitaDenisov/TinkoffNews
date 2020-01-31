package com.denisov.tinkoffnews.data.api

import com.denisov.tinkoffnews.data.dto.News
import com.denisov.tinkoffnews.data.dto.Response
import com.denisov.tinkoffnews.data.dto.Result
import com.denisov.tinkoffnews.di.scope.PerApplication
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

@PerApplication
class TinkoffApi @Inject constructor(
    private val okHttpClient: OkHttpClient
) {

    private val api by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TinkoffApiInternal::class.java)
    }

    fun getNews(): Single<List<News>> =
        api
            .getNews()
            .onErrorResumeNext { Single.error(ApiException.Connection()) }
            .doOnSuccess {
                it.resultCode
                    .takeIf { it != Result.OK }
                    ?.let { throw ApiException.Communication() }
            }
            .map { it.payload }

    fun getNewsById(id: Long) {

    }

    private interface TinkoffApiInternal {

        @GET("/v1/news")
        fun getNews(): Single<Response>

        @GET("/v1/news_content")
        fun getNewsById(
            @Query("id") newsId: Int
        ): Single<Response>
    }

    private companion object {
        private const val BASE_URL = "https://api.tinkoff.ru"
    }
}