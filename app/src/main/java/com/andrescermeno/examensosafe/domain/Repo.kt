package com.andrescermeno.examensosafe.domain

import androidx.lifecycle.LiveData
import com.andrescermeno.examensosafe.data.model.FavoritesEntity
import com.andrescermeno.examensosafe.data.model.ReviewsEntity
import com.andrescermeno.examensosafe.remote.Place
import com.andrescermeno.examensosafe.remote.Result
import com.andrescermeno.examensosafe.vo.Resource

/**
 * Creada por Andrés Cermeño el 31/8/2020
 */
interface Repo {

    suspend fun getNearbyPlaces(place : String, radius: String, keyword : String, key : String): Resource<List<Result>>

    suspend fun getDetailsPlace(id: String, fields: String, key: String): Resource<Place>

    suspend fun getAllFavoritesPlace(): List<FavoritesEntity>

    suspend fun insertFavoritesPlace(place : FavoritesEntity)

    suspend fun deleteFavoritesPlace(place: FavoritesEntity)

    suspend fun getAllReviewsForPlace(placeID : String) : List<ReviewsEntity>

    suspend fun deleteAllReviewsForPlace(placeID: String)

    suspend fun insertReview(review: ReviewsEntity)

    suspend fun getFavoritePlace(placeID: String): FavoritesEntity
}