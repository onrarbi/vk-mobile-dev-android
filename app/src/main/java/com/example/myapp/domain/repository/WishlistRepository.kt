package com.example.myapp.domain.repository

import com.example.myapp.domain.model.AppInfo
import kotlinx.coroutines.flow.Flow

interface WishlistRepository {
    fun observeAppDetails(app: AppInfo): Flow<AppInfo>

    suspend fun toggleWishlist(app: AppInfo)
}