package com.andrescermeno.examensosafe.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Creada por Andrés Cermeño el 31/8/2020
 */

interface WebService{
    @GET("place/nearbysearch/json")
    suspend fun getNearbyPlaces(
        @Query("location") place : String,
        @Query("radius") radius: String,
        @Query("keyword") keyword : String,
        @Query("key") key : String
    ): Nearby

    @GET("place/details/json")
    suspend fun getDetailsPlace(
        @Query("place_id") id: String,
        @Query("fields") fields : String,
        @Query("key") key : String
    ): Place
}