package com.denisov.tinkoffnews.presentation.adapter.models

import com.denisov.tinkoffnews.data.dto.News
import com.denisov.tinkoffnews.presentation.adapter.ViewHolderModel
import com.denisov.tinkoffnews.presentation.adapter.ViewHolderModels

class NewsViewHolderModel(
    val news: News,
    override val type: Int = ViewHolderModels.News
) : ViewHolderModel