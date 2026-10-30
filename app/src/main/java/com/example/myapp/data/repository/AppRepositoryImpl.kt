package com.example.myapp.data.repository

import com.example.myapp.data.appDtos
import com.example.myapp.data.mapper.toDomain
import com.example.myapp.domain.model.AppInfo
import com.example.myapp.domain.repository.AppsRepository

class AppRepositoryImpl : AppsRepository {
    override fun getApps(): List<AppInfo> {
        return appDtos.map { it.toDomain() }
    }
}