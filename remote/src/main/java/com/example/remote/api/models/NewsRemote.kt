package com.example.remote.api.models

import com.squareup.moshi.Json

data class NewsRemote(
    val title: String? = null,
    val media: Media?,
    @Json(name = "selftext") val body: String? = null,
    val id: String
): RemoteModel

data class Media (
    val oembed : Oembed?
)

data class Oembed (
    @Json(name = "thumbnail_url") var thumbnailUrl    : String?
)