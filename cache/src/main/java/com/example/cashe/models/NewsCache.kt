package com.example.cashe.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsCache(
    @PrimaryKey val id: String,
    val title: String?,
    val body: String?,
    val thumbnail: String?
) : CacheModel
