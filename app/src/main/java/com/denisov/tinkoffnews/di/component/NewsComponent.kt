package com.denisov.tinkoffnews.di.component

import com.denisov.tinkoffnews.di.getAppComponent
import com.denisov.tinkoffnews.di.module.NewsScreenModule
import com.denisov.tinkoffnews.di.scope.PerActivity
import com.denisov.tinkoffnews.presentation.screen.news.NewsActivity
import dagger.BindsInstance
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [NewsScreenModule::class])
interface NewsComponent {

    fun inject(activity: NewsActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun activity(activity: NewsActivity): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): NewsComponent
    }
}

fun NewsActivity.buildNewsComponent() = lazy {
    DaggerNewsComponent.builder()
        .activity(this)
        .appComponent(getAppComponent())
        .build()
}