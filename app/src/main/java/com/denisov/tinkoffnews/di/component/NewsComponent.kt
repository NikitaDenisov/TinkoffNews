package com.denisov.tinkoffnews.di.component

import android.content.Context
import com.denisov.tinkoffnews.di.ActivityContext
import com.denisov.tinkoffnews.di.getAppComponent
import com.denisov.tinkoffnews.di.module.LayoutInflaterModule
import com.denisov.tinkoffnews.di.module.NewsScreenModule
import com.denisov.tinkoffnews.di.scope.PerActivity
import com.denisov.tinkoffnews.presentation.screen.news.NewsActivity
import dagger.BindsInstance
import dagger.Component

@PerActivity
@Component(
    dependencies = [AppComponent::class],
    modules = [NewsScreenModule::class, LayoutInflaterModule::class]
)
interface NewsComponent {

    fun inject(activity: NewsActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun activity(activity: NewsActivity): Builder

        @BindsInstance
        fun context(@ActivityContext context: Context): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): NewsComponent
    }
}

fun NewsActivity.buildNewsComponent() = lazy {
    DaggerNewsComponent.builder()
        .activity(this)
        .appComponent(getAppComponent())
        .context(this)
        .build()
}