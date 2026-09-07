package com.example.data

import kotlinx.coroutines.flow.Flow

class FavoriteRepository(private val favoriteDao: FavoriteDao) {
    val favoriteIds: Flow<List<Int>> = favoriteDao.getAllFavoriteIds()

    fun isFavorite(verbId: Int): Flow<Boolean> = favoriteDao.isFavorite(verbId)

    suspend fun toggleFavorite(verbId: Int, isCurrentFavorite: Boolean) {
        if (isCurrentFavorite) {
            favoriteDao.removeFavorite(verbId)
        } else {
            favoriteDao.addFavorite(FavoriteEntity(verbId))
        }
    }
}
