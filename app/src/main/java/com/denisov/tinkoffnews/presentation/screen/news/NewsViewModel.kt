package com.denisov.tinkoffnews.presentation.screen.news

import androidx.lifecycle.MutableLiveData
import com.denisov.tinkoffnews.data.NewsRepository
import com.denisov.tinkoffnews.data.dto.News
import com.denisov.tinkoffnews.di.scope.PerActivity
import com.denisov.tinkoffnews.presentation.BaseViewModel
import com.denisov.tinkoffnews.presentation.Schedulers
import com.denisov.tinkoffnews.presentation.adapter.ViewHolderModel
import com.denisov.tinkoffnews.presentation.adapter.models.ErrorViewHolderModel
import com.denisov.tinkoffnews.presentation.adapter.models.LoadingViewHolderModel
import com.denisov.tinkoffnews.presentation.adapter.viewholders.NewsViewHolder
import com.denisov.tinkoffnews.presentation.adapter.models.NewsViewHolderModel
import javax.inject.Inject

@PerActivity
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val schedulers: Schedulers
) : BaseViewModel(), NewsViewHolder.ItemListener {

    val viewHolderModels = MutableLiveData<List<ViewHolderModel>>()
    val dataLoaded = MutableLiveData<Unit>()
    val toNewsDetails = MutableLiveData<News>()

    init {
        loadNews(false)
    }

    override fun onItemClick(news: News) {
        toNewsDetails.postValue(news)
    }

    fun onRefresh() {
        cancelSubscribes()
        loadNews(true)
    }

    private fun loadNews(force: Boolean) {
        subscribe {
            repository
                .getNews(force)
                .map { news -> news.map { NewsViewHolderModel(it) } }
                .doOnSubscribe { viewHolderModels.postValue(listOf(LoadingViewHolderModel())) }
                .doAfterTerminate { dataLoaded.postValue(Unit) }
                .subscribeOn(schedulers.io)
                .subscribe({
                    viewHolderModels.postValue(it)
                }, {
                    viewHolderModels.postValue(listOf(ErrorViewHolderModel()))
                })
        }
    }
}