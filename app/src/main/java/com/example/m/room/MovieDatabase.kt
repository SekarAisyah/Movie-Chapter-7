package com.example.m.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteMovieDao
}