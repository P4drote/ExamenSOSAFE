package com.andrescermeno.examensosafe.data.model

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 * Creada por Andrés Cermeño el 2/9/2020
 */

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favoritesEntity")
    suspend fun getAllFavoritesPlace(): List<FavoritesEntity>

    @Query("SELECT * FROM reviewsEntity WHERE place_id = :placeID")
    suspend fun getAllReviewsForPlace(placeID : String) : List<ReviewsEntity>

    @Query("DELETE FROM reviewsEntity WHERE place_id = :placeID")
    suspend fun deleteAllReviewsForPlace(placeID: String)

    @Query("SELECT * FROM favoritesEntity WHERE place_id = :placeID")
    suspend fun getFavoritePlace(placeID: String): FavoritesEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritesPlace(favorites: FavoritesEntity)

    @Delete
    suspend fun deleteFavoritesPlace(favorites: FavoritesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(review: ReviewsEntity)

}