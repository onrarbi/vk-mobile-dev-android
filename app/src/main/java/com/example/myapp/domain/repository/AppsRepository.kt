package com.example.myapp.domain.repository

import com.example.myapp.domain.model.AppInfo

interface AppsRepository {
    suspend fun getApps(): List<AppInfo>
}