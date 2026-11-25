package com.example.myapp.data.dto

data class AppInfoDto(
    val id: String,
    val name: String,
    val developer: String,
    val appCategory: String,
    val ageRating: Int,
    val size: Float,
    val icon: Int,
    val screenshotUrls: List<String>,
    val description: String
)
