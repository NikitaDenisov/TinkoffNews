package com.denisov.tinkoffnews.presentation.screen.newsdetails

import androidx.lifecycle.MutableLiveData
import com.denisov.tinkoffnews.data.NewsRepository
import com.denisov.tinkoffnews.data.dto.News
import com.denisov.tinkoffnews.presentation.BaseViewModel
import com.denisov.tinkoffnews.presentation.Schedulers
import javax.inject.Inject

class NewsDetailsViewModel @Inject constructor(
    news: News,
    repository: NewsRepository,
    schedulers: Schedulers
) : BaseViewModel() {

    val title = MutableLiveData<String>().apply { value = news.text }
    val loading = MutableLiveData<Boolean>().apply { value = true }
    val content = MutableLiveData<String>()

    init {
        subscribe {
            repository
                .getNewsById(news.id)
                .subscribeOn(schedulers.io)
                .doAfterTerminate { loading.postValue(false) }
                .subscribe({
                    content.postValue(it.content)
                }, {})
        }
    }
}