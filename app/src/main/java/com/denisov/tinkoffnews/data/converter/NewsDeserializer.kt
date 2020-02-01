package com.denisov.tinkoffnews.data.converter

import com.denisov.tinkoffnews.data.dto.News
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class NewsDeserializer : JsonDeserializer<News> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): News? = json
        ?.asJsonObject
        ?.let {
            val id = it["id"].asLong
            val date = it["publicationDate"].asJsonObject["milliseconds"].asLong
            val text = it["text"].asString

            News(
                id = id,
                text = text,
                publicationDate = date
            )
        }
}