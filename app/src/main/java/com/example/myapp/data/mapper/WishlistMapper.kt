package com.example.myapp.data.mapper

import com.example.myapp.data.local.WishlistEntity
import com.example.myapp.domain.model.AppInfo

fun WishlistEntity.toDomain(app: AppInfo): AppInfo {
    return app.copy(isInWishlist = isInWishlist)
}

fun AppInfo.toWishlistEntity(): WishlistEntity {
    return WishlistEntity(
        id = id,
        isInWishlist = isInWishlist
    )
}