package com.denisov.tinkoffnews.presentation.adapter.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denisov.tinkoffnews.R
import com.denisov.tinkoffnews.presentation.adapter.BaseViewHolder
import com.denisov.tinkoffnews.presentation.adapter.ViewHolderFactory
import com.denisov.tinkoffnews.presentation.adapter.models.LoadingViewHolderModel
import javax.inject.Inject

class LoadingViewHolder(view: View) : BaseViewHolder<LoadingViewHolderModel>(view) {

    override fun bind(model: LoadingViewHolderModel) {
    }

    class Factory @Inject constructor(layoutInflater: LayoutInflater) :
        ViewHolderFactory(layoutInflater) {

        override fun create(parent: ViewGroup) =
            LoadingViewHolder(layoutInflater.inflate(R.layout.item_loading, parent, false))
    }
}