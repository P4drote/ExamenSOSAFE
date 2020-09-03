package com.andrescermeno.examensosafe.data.model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Creada por Andrés Cermeño el 2/9/2020
 */


class DataSourceLocalImpl @Inject constructor(private val favoritesDao: FavoritesDao): DataSourceLocal {
    override suspend fun getAllFavoritesPlace(): List<FavoritesEntity> =
        withContext(Dispatchers.IO){ favoritesDao.getAllFavoritesPlace() }


    override suspend fun insertFavoritesPlace(place: FavoritesEntity) {
        withContext(Dispatchers.IO) {favoritesDao.insertFavoritesPlace(place)}
    }

    override suspend fun deleteFavoritesPlace(place: FavoritesEntity) {
        withContext(Dispatchers.IO) {favoritesDao.deleteFavoritesPlace(place)}
    }

    override suspend fun getAllReviewsForPlace(placeID: String): List<ReviewsEntity> =
        withContext(Dispatchers.IO) {favoritesDao.getAllReviewsForPlace(placeID)}


    override suspend fun deleteAllReviewsForPlace(placeID: String) {
        withContext(Dispatchers.IO) {favoritesDao.deleteAllReviewsForPlace(placeID)}
    }

    override suspend fun insertReview(review: ReviewsEntity) {
        withContext(Dispatchers.IO) {favoritesDao.insertReview(review)}
    }

    override suspend fun getFavoritePlace(placeID: String): FavoritesEntity =
        withContext(Dispatchers.IO) {favoritesDao.getFavoritePlace(placeID)}

}