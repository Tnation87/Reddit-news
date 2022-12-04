package com.example.cashe

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cashe.AppDatabase.Companion.DATABASE_VERSION
import com.example.cashe.models.NewsCache
import com.example.cashe.models.NewsCacheConverter
import com.example.cashe.news.NewsDao

@Database(
    entities = [
        NewsCache::class,
    ], version = DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(
    NewsCacheConverter::class
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        const val DATABASE_VERSION = 1

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
