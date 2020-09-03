package com.andrescermeno.examensosafe.remote

import com.andrescermeno.examensosafe.vo.Resource

/**
 * Creada por Andrés Cermeño el 31/8/2020
 */
interface DataSourceWeb {
    suspend fun getNearbyPlaces(
        place : String,
        radius: String,
        keyword : String,
        key : String
    ): Resource<List<Result>>

    suspend fun getDetailsPlace(
        id: String,
        fields: String,
        key : String
    ): Resource<Place>
}