package com.denisov.tinkoffnews.di.module

import com.denisov.tinkoffnews.di.ViewModelInject
import com.denisov.tinkoffnews.presentation.ViewModelFactorySingle
import com.denisov.tinkoffnews.presentation.getViewModel
import com.denisov.tinkoffnews.presentation.screen.newsdetails.NewsDetailsActivity
import com.denisov.tinkoffnews.presentation.screen.newsdetails.NewsDetailsViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class NewsDetailsScreenModule {

    @Provides
    @ViewModelInject
    fun provideViewModel(
        activity: NewsDetailsActivity,
        provider: Provider<NewsDetailsViewModel>
    ) = activity.getViewModel<NewsDetailsViewModel>(ViewModelFactorySingle(provider))
}