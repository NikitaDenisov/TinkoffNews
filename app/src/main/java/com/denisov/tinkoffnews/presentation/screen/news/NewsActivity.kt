package com.denisov.tinkoffnews.presentation.screen.news

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.denisov.tinkoffnews.R
import com.denisov.tinkoffnews.di.ViewModelInject
import com.denisov.tinkoffnews.di.component.buildNewsComponent
import com.denisov.tinkoffnews.presentation.adapter.RecyclerAdapter
import com.denisov.tinkoffnews.presentation.screen.newsdetails.NewsDetailsActivity
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

    private val component by buildNewsComponent()

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @Inject
    @ViewModelInject
    lateinit var viewModel: NewsViewModel
    @Inject
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_news)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
        swipeRefreshLayout = findViewById(R.id.swipeContainer)
        swipeRefreshLayout.apply {
            setOnRefreshListener {
                viewModel.onRefresh()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.viewHolderModels.observe(this, Observer { news ->
            news?.also {
                recyclerAdapter.setModels(it)
            }
        })
        viewModel.dataLoaded.observe(this, Observer {
            it?.also {
                viewModel.dataLoaded.value = null
                swipeRefreshLayout.isRefreshing = false
            }
        })
        viewModel.toNewsDetails.observe(this, Observer {
            it?.also {
                viewModel.toNewsDetails.value = null
                startActivity(
                    NewsDetailsActivity.newIntent(this, it)
                )
            }
        })
    }
}
