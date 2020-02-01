package com.denisov.tinkoffnews.presentation.adapter.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denisov.tinkoffnews.R
import com.denisov.tinkoffnews.presentation.adapter.BaseViewHolder
import com.denisov.tinkoffnews.presentation.adapter.ViewHolderFactory
import com.denisov.tinkoffnews.presentation.adapter.models.ErrorViewHolderModel
import javax.inject.Inject

class ErrorViewHolder(view: View) : BaseViewHolder<ErrorViewHolderModel>(view) {

    override fun bind(model: ErrorViewHolderModel) {
    }

    class Factory @Inject constructor(layoutInflater: LayoutInflater) : ViewHolderFactory(layoutInflater) {

        override fun create(parent: ViewGroup) = ErrorViewHolder(
            layoutInflater.inflate(R.layout.item_error, parent, false)
        )
    }
}