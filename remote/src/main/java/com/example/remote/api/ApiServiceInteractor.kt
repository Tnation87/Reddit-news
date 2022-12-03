package com.example.remote.api

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiServiceInteractor @Inject constructor(private val apiService: ApiService) {

    fun getKotlinNewsAsync() = apiService.getKotlinNewsAsync()
}