package com.denisov.tinkoffnews.data.dto

import com.denisov.tinkoffnews.data.converter.AdapterFactory
import com.denisov.tinkoffnews.data.converter.Default
import com.google.gson.annotations.JsonAdapter
import java.io.Serializable

data class Response<T>(
    val resultCode: Result,
    val payload: T
) : Serializable

@JsonAdapter(AdapterFactory::class)
enum class Result {
    OK,
    INVALID_REQUEST_DATA,
    @Default
    Unknown
}
