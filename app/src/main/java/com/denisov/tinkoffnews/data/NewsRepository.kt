package com.denisov.tinkoffnews.data

import com.denisov.tinkoffnews.data.api.TinkoffApi
import com.denisov.tinkoffnews.data.database.NewsDao
import io.reactivex.Single
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: TinkoffApi,
    private val newsDao: NewsDao
) {

    fun getNews(forceLoad: Boolean = false) = when {
        forceLoad -> loadNewsRemote()
        else -> loadNewsLocal()
    }

    fun getNewsById(id: Long) = api.getNewsById(id)

    private fun loadNewsRemote() =
        api
            .getNews()
            .doOnSuccess { newsDao.deleteAndInsertAll(it) }
            .map { it.sortedByDescending { it.publicationDate } }

    private fun loadNewsLocal() =
        Single
            .fromCallable { newsDao.getAll() }
            .flatMap {
                when {
                    it.isEmpty() -> loadNewsRemote()
                    else -> Single.just(it)
                }
            }
}