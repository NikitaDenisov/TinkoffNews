package com.denisov.tinkoffnews.di.module

import com.denisov.tinkoffnews.di.ViewModelInject
import com.denisov.tinkoffnews.presentation.ViewModelFactorySingle
import com.denisov.tinkoffnews.presentation.getViewModel
import com.denisov.tinkoffnews.presentation.screen.news.NewsActivity
import com.denisov.tinkoffnews.presentation.screen.news.NewsViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class NewsScreenModule {

    @Provides
    @ViewModelInject
    fun provideViewModel(
        activity: NewsActivity,
        provider: Provider<NewsViewModel>
    ) = activity.getViewModel<NewsViewModel>(ViewModelFactorySingle(provider))
}