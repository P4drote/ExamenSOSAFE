package com.andrescermeno.examensosafe.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Creada por Andrés Cermeño el 2/9/2020
 */

@Entity(tableName = "reviewsEntity")
data class ReviewsEntity (
    @PrimaryKey(autoGenerate = true)
    val idReview : Long = 0L,
    @ColumnInfo(name = "place_id")
    val placeID: String,
    val imgAuthor : String,
    val nameAuthor : String,
    val rating : Double,
    val time : String,
    val opinion : String,
)