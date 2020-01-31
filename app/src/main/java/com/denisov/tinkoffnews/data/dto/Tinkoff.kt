package com.denisov.tinkoffnews.data.dto

import com.denisov.tinkoffnews.data.converter.AdapterFactory
import com.denisov.tinkoffnews.data.converter.Default
import com.google.gson.annotations.JsonAdapter
import java.io.Serializable

data class News(
    val id: Long,
    val name: String,
    val text: String,
    val publicationDate: PublicationDate
) : Serializable

data class Response(
    val resultCode: Result,
    val payload: List<News>
) : Serializable

@JsonAdapter(AdapterFactory::class)
enum class Result {
    OK,
    INVALID_REQUEST_DATA,
    @Default
    Unknown
}

data class PublicationDate(val milliseconds: Long) : Serializable
