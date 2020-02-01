package com.denisov.tinkoffnews.presentation.adapter.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.denisov.tinkoffnews.R
import com.denisov.tinkoffnews.data.dto.News
import com.denisov.tinkoffnews.presentation.adapter.BaseViewHolder
import com.denisov.tinkoffnews.presentation.adapter.ViewHolderFactory
import com.denisov.tinkoffnews.presentation.adapter.models.NewsViewHolderModel
import javax.inject.Inject

class NewsViewHolder(
    view: View,
    listener: ItemListener
) : BaseViewHolder<NewsViewHolderModel>(view) {

    private var model: News? = null

    init {
        view.setOnClickListener {
            model?.let {
                listener.onItemClick(it)
            }
        }
    }

    override fun bind(model: NewsViewHolderModel) {
        this.model = model.news.also { news ->
            (itemView as? TextView)?.apply {
                text = HtmlCompat.fromHtml(news.text, HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS)
            }
        }
    }

    interface ItemListener {
        fun onItemClick(news: News)
    }

    class Factory @Inject constructor(
        layoutInflater: LayoutInflater,
        private val listener: ItemListener
    ) : ViewHolderFactory(layoutInflater) {

        override fun create(parent: ViewGroup) =
            NewsViewHolder(
                layoutInflater.inflate(R.layout.item_news, parent, false),
                listener
            )
    }
}