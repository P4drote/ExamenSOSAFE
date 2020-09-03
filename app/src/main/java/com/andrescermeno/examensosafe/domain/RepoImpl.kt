package com.andrescermeno.examensosafe.domain

import androidx.lifecycle.LiveData
import com.andrescermeno.examensosafe.data.model.DataSourceLocalImpl
import com.andrescermeno.examensosafe.data.model.FavoritesEntity
import com.andrescermeno.examensosafe.data.model.ReviewsEntity
import com.andrescermeno.examensosafe.remote.DataSourceWebImp
import com.andrescermeno.examensosafe.remote.Place
import com.andrescermeno.examensosafe.remote.Result
import com.andrescermeno.examensosafe.vo.Resource
import javax.inject.Inject

/**
 * Creada por Andrés Cermeño el 31/8/2020
 */
class RepoImpl @Inject constructor(private val dataSourceWeb: DataSourceWebImp, private val dataSourceLocal: DataSourceLocalImpl): Repo {

    override suspend fun getNearbyPlaces(
        place: String,
        radius: String,
        keyword: String,
        key: String
    ): Resource<List<Result>> {
        return dataSourceWeb.getNearbyPlaces(place, radius, keyword, key)
    }

    override suspend fun getDetailsPlace(id: String, fields: String, key: String): Resource<Place> {
        return dataSourceWeb.getDetailsPlace(id,fields,key)
    }

    override suspend fun getAllFavoritesPlace(): List<FavoritesEntity> {
        return  dataSourceLocal.getAllFavoritesPlace()
    }

    override suspend fun insertFavoritesPlace(place: FavoritesEntity) {
        dataSourceLocal.insertFavoritesPlace(place)
    }

    override suspend fun deleteFavoritesPlace(place: FavoritesEntity) {
        dataSourceLocal.deleteFavoritesPlace(place)
    }

    override suspend fun getAllReviewsForPlace(placeID: String): List<ReviewsEntity> {
        return dataSourceLocal.getAllReviewsForPlace(placeID)
    }

    override suspend fun deleteAllReviewsForPlace(placeID: String) {
        dataSourceLocal.deleteAllReviewsForPlace(placeID)
    }

    override suspend fun insertReview(review: ReviewsEntity) {
        dataSourceLocal.insertReview(review)
    }

    override suspend fun getFavoritePlace(placeID: String): FavoritesEntity {
        return dataSourceLocal.getFavoritePlace(placeID)
    }
}