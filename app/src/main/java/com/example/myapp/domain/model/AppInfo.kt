package com.example.myapp.domain.model

enum class AppCategory(val categoryName: String) {
    VIDEO("Videos"),
    MUSIC("Music"),
    BOOKS("Books"),
    SOCIAL("Social networks"),
    GAMES("Games")
}

data class AppInfo(
    val id: String,
    val name: String,
    val developer: String,
    val appCategory: AppCategory,
    val ageRating: Int,
    val size: Float,
    val icon: Int,
    val screenshotUrls: List<String>,
    val description: String
)