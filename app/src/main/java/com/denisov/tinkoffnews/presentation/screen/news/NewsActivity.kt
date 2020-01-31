package com.denisov.tinkoffnews.presentation.screen.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.denisov.tinkoffnews.R
import com.denisov.tinkoffnews.di.ViewModelInject
import com.denisov.tinkoffnews.di.component.buildNewsComponent
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

    private val component by buildNewsComponent()

    @Inject
    @ViewModelInject
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        setContentView(R.layout.activity_main)
    }
}
