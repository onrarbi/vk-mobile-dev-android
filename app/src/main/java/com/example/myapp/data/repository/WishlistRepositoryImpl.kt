package com.example.myapp.data.repository

import com.example.myapp.data.local.WishlistDao
import com.example.myapp.data.mapper.toDomain
import com.example.myapp.data.mapper.toWishlistEntity
import com.example.myapp.domain.model.AppInfo
import com.example.myapp.domain.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WishlistRepositoryImpl @Inject constructor(
    private val dao: WishlistDao
) : WishlistRepository {

    override fun observeAppDetails(app: AppInfo): Flow<AppInfo> {
        return dao.observeWishlist(app.id).map { entity ->
            entity?.toDomain(app)
                ?: app.copy(isInWishlist = false)
        }
    }

    override suspend fun toggleWishlist(app: AppInfo) {
        val current = dao.getWishlist(app.id)?.isInWishlist ?: false

        dao.insertIfAbsent(
            app.copy(isInWishlist = false).toWishlistEntity()
        )

        dao.updateWishlistStatus(
            id = app.id,
            isInWishlist = !current
        )
    }
}