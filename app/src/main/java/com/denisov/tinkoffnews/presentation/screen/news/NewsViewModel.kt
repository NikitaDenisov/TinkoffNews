package com.denisov.tinkoffnews.presentation.screen.news

import com.denisov.tinkoffnews.data.api.TinkoffApi
import com.denisov.tinkoffnews.presentation.BaseViewModel
import com.denisov.tinkoffnews.presentation.Schedulers
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val api: TinkoffApi,
    private val schedulers: Schedulers
) : BaseViewModel() {

    init {
        subscribe {
            api
                .getNews()
                .subscribeOn(schedulers.io)
                .subscribe({
                    val ii = 0
                }, {})
        }
    }
}