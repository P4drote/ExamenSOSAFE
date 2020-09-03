package com.andrescermeno.examensosafe.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Creada por Andrés Cermeño el 2/9/2020
 */

@Entity(tableName = "favoritesEntity")
data class FavoritesEntity (
    @PrimaryKey
    @ColumnInfo(name = "place_id")
    val placeID: String,
    @ColumnInfo(name = "place_name")
    val placeName: String,
    val picture : String,
    val rating : Double,
    val direction : String,
    val latitude: Double,
    val longitude: Double
)