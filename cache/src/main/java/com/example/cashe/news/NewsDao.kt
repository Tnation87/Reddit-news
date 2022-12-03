package com.example.cashe.news

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cashe.models.NewsCache

@Dao
interface NewsDao {

    @Query("SELECT * from NewsCache")
    fun get(): List<NewsCache>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<NewsCache>)

}
