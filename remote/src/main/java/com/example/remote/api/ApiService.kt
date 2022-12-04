package com.example.remote.api

import com.example.remote.api.models.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("r/kotlin/.json")
    fun getKotlinNewsAsync(): Deferred<NewsResponse>
}