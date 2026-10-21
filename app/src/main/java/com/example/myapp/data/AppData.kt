package com.example.myapp.data

import com.example.myapp.R
import com.example.myapp.model.AppCategory
import com.example.myapp.model.AppInfo

internal val apps = listOf(
    AppInfo(
        id = "youtube",
        name = "YouTube",
        developer = "Google LLC",
        appCategory = AppCategory.VIDEO,
        ageRating = 12,
        size = 150.0f,
        icon = R.drawable.youtube_icon,
        screenshotUrls = listOf(
            "https://picsum.photos/id/1018/960/540",
            "https://picsum.photos/id/1015/960/540",
            "https://picsum.photos/id/1016/960/540"
        ),
        description = "Official application for the world's largest video-sharing platform."
    ),

    AppInfo(
        id = "twitch",
        name = "Twitch",
        developer = "Twitch Interactive, Inc.",
        appCategory = AppCategory.VIDEO,
        ageRating = 13,
        size = 120.0f,
        icon = R.drawable.twitch_icon,
        screenshotUrls = listOf(
            "https://picsum.photos/id/1018/960/540",
            "https://picsum.photos/id/1015/960/540",
            "https://picsum.photos/id/1016/960/540"
        ),
        description = "Official application for the world's leading live-streaming and gaming community platform."
    ),

    AppInfo(
        id = "youtube_music",
        name = "YouTube Music",
        developer = "Google LLC",
        appCategory = AppCategory.MUSIC,
        ageRating = 12,
        size = 110.0f,
        icon = R.drawable.youtube_music_icon,
        screenshotUrls = listOf(
            "https://picsum.photos/id/1018/960/540",
            "https://picsum.photos/id/1015/960/540",
            "https://picsum.photos/id/1016/960/540"
        ),
        description = "Official application for the world's largest catalog of official tracks, live performances, and music videos."
    ),

    AppInfo(
        id = "spotify",
        name = "Spotify",
        developer = "Spotify AB",
        appCategory = AppCategory.MUSIC,
        ageRating = 12,
        size = 95.0f,
        icon = R.drawable.spotify_icon,
        screenshotUrls = listOf(
            "https://picsum.photos/id/1018/960/540",
            "https://picsum.photos/id/1015/960/540",
            "https://picsum.photos/id/1016/960/540"
        ),
        description = "Official application for the world's leading music streaming, personalized discovery, and podcast platform."
    ),

    AppInfo(
        id = "apple_books",
        name = "Apple Books",
        developer = "Apple Inc.",
        appCategory = AppCategory.BOOKS,
        ageRating = 4,
        size = 85.0f,
        icon = R.drawable.apple_books_icon,
        screenshotUrls = listOf(
            "https://picsum.photos/id/1018/960/540",
            "https://picsum.photos/id/1015/960/540",
            "https://picsum.photos/id/1016/960/540"
        ),
        description = "Official application for the world's premier digital reading, curated bookstore, and audiobook platform."
    ),

    AppInfo(
        id = "google_books",
        name = "Google Books",
        developer = "Google LLC",
        appCategory = AppCategory.BOOKS,
        ageRating = 12,
        size = 75.0f,
        icon = R.drawable.google_books_icon,
        screenshotUrls = listOf(
            "https://picsum.photos/id/1018/960/540",
            "https://picsum.photos/id/1015/960/540",
            "https://picsum.photos/id/1016/960/540"
        ),
        description = "Official application for the world's largest catalog of digital textbooks, public domain classics, and modern ebooks."
    ),

    AppInfo(
        id = "instagram",
        name = "Instagram",
        developer = "Instagram",
        appCategory = AppCategory.SOCIAL,
        ageRating = 13,
        size = 140.0f,
        icon = R.drawable.instagram_icon,
        screenshotUrls = listOf(
            "https://picsum.photos/id/1018/960/540",
            "https://picsum.photos/id/1015/960/540",
            "https://picsum.photos/id/1016/960/540"
        ),
        description = "Official application for the world's leading visual social network, content creation, and creator community platform."
    ),

    AppInfo(
        id = "telegram",
        name = "Telegram",
        developer = "Telegram FZ-LLC",
        appCategory = AppCategory.SOCIAL,
        ageRating = 16,
        size = 90.0f,
        icon = R.drawable.telegram_icon,
        screenshotUrls = listOf(
            "https://picsum.photos/id/1018/960/540",
            "https://picsum.photos/id/1015/960/540",
            "https://picsum.photos/id/1016/960/540"
        ),
        description = "Official application for the world's premier secure messaging, large-scale community channels, and cloud storage platform."
    ),

    AppInfo(
        id = "steam",
        name = "Steam",
        developer = "Valve Corporation",
        appCategory = AppCategory.GAMES,
        ageRating = 12,
        size = 60.0f,
        icon = R.drawable.steam_icon,
        screenshotUrls = listOf(
            "https://picsum.photos/id/1018/960/540",
            "https://picsum.photos/id/1015/960/540",
            "https://picsum.photos/id/1016/960/540"
        ),
        description = "Official application for the world's premier PC gaming, store, and community platform."
    )
)