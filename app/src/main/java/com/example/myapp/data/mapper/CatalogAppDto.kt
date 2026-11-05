package com.example.myapp.data.mapper

import com.example.myapp.data.dto.CatalogAppDto
import com.example.myapp.domain.model.AppInfo

fun CatalogAppDto.toDomain(): AppInfo {
    return AppInfo(
        id = id,
        name = name,
        appCategory = category,
        iconUrl = iconUrl,
        description = description
    )
}