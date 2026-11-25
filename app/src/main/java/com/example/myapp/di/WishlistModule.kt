package com.example.myapp.di

import com.example.myapp.data.repository.WishlistRepositoryImpl
import com.example.myapp.domain.repository.WishlistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WishlistModule {

    @Binds
    abstract fun bindWishlistRepository(
        impl: WishlistRepositoryImpl
    ): WishlistRepository
}