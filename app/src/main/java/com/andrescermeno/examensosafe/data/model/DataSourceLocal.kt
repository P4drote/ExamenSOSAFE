package com.andrescermeno.examensosafe.data.model

import androidx.lifecycle.LiveData
import com.andrescermeno.examensosafe.vo.Resource

/**
 * Creada por Andrés Cermeño el 2/9/2020
 */
interface DataSourceLocal {

    suspend fun getAllFavoritesPlace(): List<FavoritesEntity>
    suspend fun insertFavoritesPlace(place : FavoritesEntity)
    suspend fun deleteFavoritesPlace(place: FavoritesEntity)
    suspend fun getAllReviewsForPlace(placeID : String) : List<ReviewsEntity>
    suspend fun deleteAllReviewsForPlace(placeID: String)
    suspend fun insertReview(review: ReviewsEntity)
    suspend fun getFavoritePlace(placeID: String): FavoritesEntity
}