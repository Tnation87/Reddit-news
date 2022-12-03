package com.example.remote.api.models

import com.squareup.moshi.Json

data class NewsResponse(
    val data : Data?
)

data class Data (
    val children  : ArrayList<Child>
)

data class Child (
    @Json(name = "data") val newsItem : NewsRemote?
)