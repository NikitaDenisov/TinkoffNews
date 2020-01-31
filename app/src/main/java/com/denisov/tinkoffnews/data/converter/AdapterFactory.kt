package com.denisov.tinkoffnews.data.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken

@Suppress("UNCHECKED_CAST")
class AdapterFactory : TypeAdapterFactory {

    override fun <T : Any?> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
        return when {
            EnumTypeAdapter.isAdapter(type) -> EnumTypeAdapter(type)
            else -> null
        }
    }
}