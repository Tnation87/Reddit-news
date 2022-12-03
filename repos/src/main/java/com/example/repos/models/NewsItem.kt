package com.example.repos.models

data class NewsItem(
    val id: String,
    val title: String?,
    val thumbnail: String?,
    val body: String?,
) : ItemModel