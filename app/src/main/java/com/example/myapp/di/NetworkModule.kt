package com.example.myapp.di

import com.example.myapp.data.remote.CatalogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://185.103.109.134/").addConverterFactory(
            GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideCatalogApi(retrofit: Retrofit): CatalogApi {
        return retrofit.create(CatalogApi::class.java)
    }
}