package com.andrescermeno.examensosafe.remote

import com.andrescermeno.examensosafe.vo.Resource
import javax.inject.Inject

/**
 * Creada por Andrés Cermeño el 31/8/2020
 */
class DataSourceWebImp @Inject constructor (private val webService: WebService) : DataSourceWeb{

    override suspend fun getNearbyPlaces(
        place: String,
        radius: String,
        keyword: String,
        key: String
    ): Resource<List<Result>> {
        return Resource.Success(webService.getNearbyPlaces(place, radius, keyword, key).results)
    }

    override suspend fun getDetailsPlace(id: String, fields: String, key: String): Resource<Place> {
        return Resource.Success(webService.getDetailsPlace(id,fields,key))
    }
}