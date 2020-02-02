package com.denisov.tinkoffnews.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.denisov.tinkoffnews.data.dto.News

@Dao
interface NewsDao {

    @Query("SELECT * FROM news ORDER BY publicationDate DESC")
    fun getAll(): List<News>

    @Insert
    fun insertAll(news: List<News>)

    @Query("DELETE FROM news")
    fun deleteAll()

    @Transaction
    fun deleteAndInsertAll(news: List<News>) {
        deleteAll()
        insertAll(news)
    }
}