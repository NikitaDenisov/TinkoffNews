package com.denisov.tinkoffnews.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.denisov.tinkoffnews.data.dto.News

@Database(entities = [News::class], version = VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}

const val DATABASE_NAME = "tinkoff_news"
const val VERSION = 1