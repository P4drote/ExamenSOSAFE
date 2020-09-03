package com.andrescermeno.examensosafe.data.model

import androidx.room.Database
import androidx.room.RoomDatabase


/**
 * Creada por Andrés Cermeño el 2/9/2020
 */
@Database(entities = [FavoritesEntity::class, ReviewsEntity::class], version = 1, exportSchema = false)
abstract class FavoriteDataBase: RoomDatabase() {

    abstract fun favoriteDAO(): FavoritesDao

}