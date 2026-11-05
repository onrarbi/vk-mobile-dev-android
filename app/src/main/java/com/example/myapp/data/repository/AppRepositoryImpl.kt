package com.example.myapp.data.repository

import com.example.myapp.data.mapper.toDomain
import com.example.myapp.data.remote.CatalogApi
import com.example.myapp.domain.model.AppInfo
import com.example.myapp.domain.repository.AppsRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val api: CatalogApi): AppsRepository {
    override suspend fun getApps(): List<AppInfo> {
        val catalog = api.getCatalog()
        return catalog.map { it.toDomain() }
    }
}