package com.guicarneirodev.hoopreel.feature.favorites.domain

import com.guicarneirodev.hoopreel.core.database.dao.FavoriteDao
import com.guicarneirodev.hoopreel.core.database.entity.FavoriteEntity
import com.guicarneirodev.hoopreel.feature.favorites.domain.model.Video
import kotlinx.coroutines.flow.Flow

open class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    open fun getAllFavorites(): Flow<List<FavoriteEntity>> {
        return favoriteDao.getAllFavorites()
    }

    open fun isFavorite(videoId: String): Flow<Boolean> {
        return favoriteDao.isFavorite(videoId)
    }

    open suspend fun addFavorite(video: Video, playerName: String) {
        val favoriteEntity = FavoriteEntity(
            videoId = video.id,
            playerName = playerName,
            title = video.title,
            thumbnailUrl = video.thumbnailUrl,
            viewCount = video.statistics?.viewCount,
            likeCount = video.statistics?.likeCount
        )
        favoriteDao.insertFavorite(favoriteEntity)
    }

    open suspend fun removeFavorite(videoId: String) {
        favoriteDao.deleteFavoriteById(videoId)
    }

    open suspend fun toggleFavorite(video: Video, playerName: String, isFavorite: Boolean) {
        if (isFavorite) {
            removeFavorite(video.id)
        } else {
            addFavorite(video, playerName)
        }
    }
}