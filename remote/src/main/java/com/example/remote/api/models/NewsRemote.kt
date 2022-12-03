package com.example.remote.api.models

import com.squareup.moshi.Json

data class NewsRemote(
    val title: String? = null,
    val thumbnail: String? = null,
    @Json(name = "selftext") val body: String? = null,
    val id: String
): RemoteModel