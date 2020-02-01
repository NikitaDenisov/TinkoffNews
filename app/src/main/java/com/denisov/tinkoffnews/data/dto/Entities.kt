package com.denisov.tinkoffnews.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "news")
data class News(
    @PrimaryKey
    val id: Long,
    val text: String,
    val publicationDate: Long
) : Serializable

data class NewsDescription(
    val content: String
) : Serializable