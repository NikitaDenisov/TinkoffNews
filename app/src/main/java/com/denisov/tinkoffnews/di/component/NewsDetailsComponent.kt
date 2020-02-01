package com.denisov.tinkoffnews.di.component

import com.denisov.tinkoffnews.data.dto.News
import com.denisov.tinkoffnews.di.getAppComponent
import com.denisov.tinkoffnews.di.module.NewsDetailsScreenModule
import com.denisov.tinkoffnews.di.scope.PerActivity
import com.denisov.tinkoffnews.presentation.screen.newsdetails.NewsDetailsActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [AppComponent::class],
    modules = [NewsDetailsScreenModule::class]
)
@PerActivity
interface NewsDetailsComponent {

    fun inject(activity: NewsDetailsActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun activity(activity: NewsDetailsActivity): Builder

        @BindsInstance
        fun news(news: News): Builder

        fun appComponent(component: AppComponent): Builder

        fun build(): NewsDetailsComponent
    }
}

fun NewsDetailsActivity.buildComponent() = lazy {
    DaggerNewsDetailsComponent.builder()
        .activity(this)
        .appComponent(getAppComponent())
        .news(intent.getSerializableExtra(NewsDetailsActivity.NEWS_EXTRA) as News)
        .build()
}