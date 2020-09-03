package com.andrescermeno.examensosafe.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Creada por Andrés Cermeño el 1/9/2020
 */



@Parcelize
data class Nearby(
    @SerializedName("results")
    val results: List<Result> = listOf()
):Parcelable

data class NearbyList(
    @SerializedName("results")
    val nearbyList: List<Nearby> = listOf()
)

@Parcelize
data class Result(
    val geometry: Geometry,
    val icon: String,
    val id: String,
    val name: String,
    val opening_hours: OpeningHours,
    val photos: List<Photo>,
    val place_id: String,
    val rating: Double,
    val reference: String,
    val types: List<String>,
    val user_ratings_total: Int,
    val vicinity: String
):Parcelable

@Parcelize
data class Geometry(
    val location: Location,
    val viewport: Viewport
):Parcelable

@Parcelize
data class Viewport(
    val northeast: Northeast,
    val southwest: Southwest
):Parcelable

@Parcelize
data class Northeast(
    val lat: Double,
    val lng: Double
):Parcelable

@Parcelize
data class Southwest(
    val lat: Double,
    val lng: Double
):Parcelable

@Parcelize
data class OpeningHours(
    val open_now: Boolean
): Parcelable

@Parcelize
data class Photo(
    val height: Int,
    val html_attributions: List<String>,
    val photo_reference: String,
    val width: Int
): Parcelable

@Parcelize
data class Location(
    val lat: Double,
    val lng: Double
):Parcelable