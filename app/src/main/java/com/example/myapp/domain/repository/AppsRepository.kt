package com.example.myapp.domain.repository

import com.example.myapp.domain.model.AppInfo

interface AppsRepository {
    suspend fun getApps(): List<AppInfo>

    suspend fun getRating(id: String): Float

    suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float>

    suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float>
}