package com.example.repos.injection

import com.example.cashe.news.NewsCacheInteractor
import com.example.remote.api.ApiServiceInteractor
import com.example.repos.news.NewsRepo
import com.example.repos.news.NewsRepoImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ReposModule {

    @Provides
    @Singleton
    fun provideNewsRepo(
        newsCacheInteractor: NewsCacheInteractor,
        apiService: ApiServiceInteractor
    ): NewsRepo {
        return NewsRepoImp(
            apiService,
            newsCacheInteractor
        )
    }

}