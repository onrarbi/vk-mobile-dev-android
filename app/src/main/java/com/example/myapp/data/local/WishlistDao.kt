package com.example.myapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {

    @Query("SELECT * FROM wishlist WHERE id = :id")
    fun observeWishlist(id: String): Flow<WishlistEntity?>

    @Query("SELECT * FROM wishlist WHERE id = :id")
    suspend fun getWishlist(id: String): WishlistEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIfAbsent(entity: WishlistEntity)

    @Query("UPDATE wishlist SET isInWishlist = :isInWishlist WHERE id = :id")
    suspend fun updateWishlistStatus(
        id: String,
        isInWishlist: Boolean
    )
}