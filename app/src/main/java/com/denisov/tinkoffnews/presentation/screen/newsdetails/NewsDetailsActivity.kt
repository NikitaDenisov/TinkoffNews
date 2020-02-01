package com.denisov.tinkoffnews.presentation.screen.newsdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.denisov.tinkoffnews.R
import com.denisov.tinkoffnews.data.dto.News
import com.denisov.tinkoffnews.di.ViewModelInject
import com.denisov.tinkoffnews.di.component.buildComponent
import javax.inject.Inject


class NewsDetailsActivity : AppCompatActivity() {

    private val component by buildComponent()

    private lateinit var toolbar: Toolbar
    private lateinit var contentTextView: TextView
    private lateinit var progressView: View

    @Inject
    @ViewModelInject
    lateinit var viewModel: NewsDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_news_details)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        progressView = findViewById(R.id.progress_bar)

        contentTextView = findViewById(R.id.content)
        contentTextView.movementMethod = ScrollingMovementMethod()

        toolbar = findViewById(R.id.toolbar)
        toolbar.apply {
            setSupportActionBar(this)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
            setNavigationOnClickListener { finish() }
        }
    }

    private fun observeViewModel() {
        viewModel.title.observe(this, Observer { title ->
            title
                ?.takeIf { it.isNotBlank() }
                ?.also { supportActionBar?.title = it }
        })
        viewModel.content.observe(this, Observer { content ->
            content
                ?.takeIf { it.isNotBlank() }
                ?.let { Html.fromHtml(it) }
                ?.also { contentTextView.text = it }
        })
        viewModel.loading.observe(this, Observer { loading ->
            progressView.visibility = if (loading == true) View.VISIBLE else View.GONE
        })
    }

    companion object {
        const val NEWS_EXTRA = "NEWS_EXTRA"

        fun newIntent(context: Context, news: News) =
            Intent(context, NewsDetailsActivity::class.java).apply {
                putExtra(NEWS_EXTRA, news)
            }
    }
}