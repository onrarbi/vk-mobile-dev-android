package com.example.myapp.data.remote

import com.example.myapp.data.dto.CatalogAppDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CatalogApi {
    @GET("catalog")
    suspend fun getCatalog(): List<CatalogAppDto>

    @GET("{id}")
    suspend fun getAppById(@Path("id") id: String): CatalogAppDto
}