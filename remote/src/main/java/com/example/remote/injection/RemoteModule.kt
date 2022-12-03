package com.example.remote.injection

import android.app.Application
import com.example.remote.api.ApiService
import com.example.remote.factory.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.remote.factory.UnitConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteModule {

    @Provides
    @Named("ApiTimeout")
    fun provideApiTimeout(): Long = 30_000 // 30 Seconds

    @Provides
    @Singleton
    fun provideMoshi(application: Application): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()) //keep this at the end
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        moshi: Moshi,
        client: OkHttpClient,
        @Named("BASE_URL_API") baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(UnitConverterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    @Provides
    @Named("BASE_URL_API")
    fun provideAPIBaseUrl(): String {
        return BASE_URL_API
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit, apiServiceHolder: APIServiceHolder): ApiService {
        val service = retrofit.create(ApiService::class.java)
        apiServiceHolder.apiService = service
        return service
    }

    @Provides
    @Singleton
    fun provideApiServiceHolder(): APIServiceHolder {
        return APIServiceHolder()
    }

    companion object {
        private const val BASE_URL_API = "https://www.reddit.com/"
    }
}