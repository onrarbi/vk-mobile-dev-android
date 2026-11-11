package com.example.myapp.di

import com.example.myapp.data.repository.AppRepositoryImpl
import com.example.myapp.domain.repository.AppsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAppsRepository(impl: AppRepositoryImpl): AppsRepository
}