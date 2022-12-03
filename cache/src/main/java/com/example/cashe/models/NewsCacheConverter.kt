package com.example.cashe.models

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class NewsCacheConverter {

    private val adapter = Moshi.Builder().build().adapter<List<NewsCache>>(Types.newParameterizedType(List::class.java, NewsCache::class.java))

    @TypeConverter
    fun fromJson(string: String): List<NewsCache>? {
        return adapter.fromJson(string)
    }

    @TypeConverter
    fun toJson(news: List<NewsCache>): String? {
        return adapter.toJson(news)
    }

}