package com.example.myapp.data.mapper

import com.example.myapp.data.dto.AppInfoDto
import com.example.myapp.domain.model.AppCategory
import com.example.myapp.domain.model.AppInfo

fun AppInfoDto.toDomain(): AppInfo {
    return AppInfo(
        id = id,
        name = name,
        developer = developer,
        appCategory = AppCategory.valueOf(appCategory),
        ageRating = ageRating,
        size = size,
        icon = icon,
        screenshotUrls = screenshotUrls,
        description = description
    )
}