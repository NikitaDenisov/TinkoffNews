package com.denisov.tinkoffnews.data.converter

import com.google.gson.TypeAdapter
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.util.TreeMap
import kotlin.collections.set
import kotlin.collections.HashMap

@Suppress("UNCHECKED_CAST")
internal class EnumTypeAdapter<T>(type: TypeToken<T>) : TypeAdapter<T>() {
    private val nameToConstant = TreeMap<String, Enum<*>>(String.CASE_INSENSITIVE_ORDER)
    private val constantToName = HashMap<Enum<*>, String>()
    private var defaultEnum: Enum<*>? = null

    init {
        val classOfT = type.rawType?.takeIf { !type.rawType.isEnum }?.superclass ?: type.rawType
        try {
            for (constant in classOfT.enumConstants!!.map { it as Enum<*> }) {
                val name = constant.name
                val serializedNameAnnotation = classOfT.getField(name).getAnnotation(SerializedName::class.java)
                val defaultAnnotation =
                    classOfT.getField(name).annotations.any { it is Default }

                nameToConstant[name] = constant
                constantToName[constant] = serializedNameAnnotation?.value ?: name
                serializedNameAnnotation?.let {
                    nameToConstant[it.value] = constant
                    it.alternate.forEach {
                        nameToConstant[it] = constant
                    }
                }
                if (defaultAnnotation) {
                    defaultEnum = constant
                }
            }
        } catch (e: NoSuchFieldException) {
            throw AssertionError("Missing field in " + classOfT.name, e)
        }
    }

    @Throws(IOException::class)
    override fun read(reader: JsonReader): T? {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return defaultEnum as T?
        }
        return (nameToConstant[reader.nextString()] ?: defaultEnum) as T?
    }

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: T?) {
        out.value(if (value == null) null else constantToName[value as Enum<*>])
    }

    companion object {
        fun <T> isAdapter(type: TypeToken<T>) =
            Enum::class.java.isAssignableFrom(type.rawType) && type.rawType != Enum::class.java
    }
}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Default