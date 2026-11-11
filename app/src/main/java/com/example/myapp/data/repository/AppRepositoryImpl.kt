package com.example.myapp.data.repository

import com.example.myapp.data.mapper.toDomain
import com.example.myapp.data.remote.CatalogApi
import com.example.myapp.domain.model.AppInfo
import com.example.myapp.domain.repository.AppsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val api: CatalogApi): AppsRepository {
    override suspend fun getApps(): List<AppInfo> {
        val catalog = api.getCatalog()
        return catalog.map { it.toDomain() }
    }

    override suspend fun getRating(id: String): Float {
        delay(1500)
        return (30..50).random() / 10f
    }

    override suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float> {
        val ratings = mutableMapOf<String, Float>()

        for (id in ids) {
            ratings[id] = getRating(id)
        }

        return ratings
    }

    override suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float> = coroutineScope {
        ids.map { id ->
            async {
                id to getRating(id)
            }
        }.awaitAll().toMap()
    }
}