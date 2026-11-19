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
    val developer: String? = null,
    val appCategory: String,
    val ageRating: Int? = null,
    val size: Float? = null,
    val icon: Int? = null,
    val iconUrl: String? = null,
    val screenshotUrls: List<String> = emptyList(),
    val description: String
)