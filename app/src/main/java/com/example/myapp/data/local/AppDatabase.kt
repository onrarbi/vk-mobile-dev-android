package com.example.myapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WishlistEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wishlistDao(): WishlistDao
}