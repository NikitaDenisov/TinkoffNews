package com.denisov.tinkoffnews.di.module

import com.denisov.tinkoffnews.di.ViewModelInject
import com.denisov.tinkoffnews.di.scope.PerActivity
import com.denisov.tinkoffnews.presentation.ViewModelFactorySingle
import com.denisov.tinkoffnews.presentation.adapter.ViewHolderFactory
import com.denisov.tinkoffnews.presentation.adapter.ViewHolderModels
import com.denisov.tinkoffnews.presentation.adapter.viewholders.ErrorViewHolder
import com.denisov.tinkoffnews.presentation.adapter.viewholders.LoadingViewHolder
import com.denisov.tinkoffnews.presentation.adapter.viewholders.NewsViewHolder
import com.denisov.tinkoffnews.presentation.getViewModel
import com.denisov.tinkoffnews.presentation.screen.news.NewsActivity
import com.denisov.tinkoffnews.presentation.screen.news.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module(includes = [NewsScreenModule.BindsModule::class])
class NewsScreenModule {

    @Provides
    @ViewModelInject
    fun provideViewModel(
        activity: NewsActivity,
        provider: Provider<NewsViewModel>
    ) = activity.getViewModel<NewsViewModel>(ViewModelFactorySingle(provider))

    @Module
    interface BindsModule {

        @Binds
        @IntoMap
        @IntKey(ViewHolderModels.News)
        fun bindsNewsViewHolder(factory: NewsViewHolder.Factory): ViewHolderFactory

        @Binds
        @IntoMap
        @IntKey(ViewHolderModels.Loading)
        fun bindsLoadingViewHolder(factory: LoadingViewHolder.Factory): ViewHolderFactory

        @Binds
        @IntoMap
        @IntKey(ViewHolderModels.Error)
        fun bindsErrorViewHolder(factory: ErrorViewHolder.Factory): ViewHolderFactory

        @Binds
        fun bindNewListener(viewModel: NewsViewModel): NewsViewHolder.ItemListener
    }
}