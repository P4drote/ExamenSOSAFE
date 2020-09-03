package com.andrescermeno.examensosafe.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Creada por Andrés Cermeño el 2/9/2020
 */

data class Place(
    @SerializedName("result")
    val result: Data,
)

@Parcelize
data class Data(
    val formatted_address: String,
    val geometry: Geometry,
    val name: String,
    val photos: List<Photo>,
    val rating: Double,
    val reviews: List<Review>
):Parcelable

@Parcelize
data class Review(
    val author_name: String,
    val profile_photo_url: String,
    val rating: Double,
    val relative_time_description: String,
    val text: String,
):Parcelable
