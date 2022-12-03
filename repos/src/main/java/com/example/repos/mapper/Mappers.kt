package com.example.repos.mapper

object Mappers {
    val newsRemoteMapper by lazy { NewsRemoteMapper() }
    val newsCacheMapper by lazy { NewsCacheMapper() }
}