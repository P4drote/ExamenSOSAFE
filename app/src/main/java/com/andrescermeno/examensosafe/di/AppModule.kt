package com.andrescermeno.examensosafe.di

import android.content.Context
import androidx.room.Room
import com.andrescermeno.examensosafe.data.model.FavoriteDataBase
import com.andrescermeno.examensosafe.remote.WebService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Creada por Andrés Cermeño el 31/8/2020
 */

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance() = Retrofit.Builder()
        .baseUrl("https://maps.googleapis.com/maps/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()


    @Singleton
    @Provides
    fun provideWebService(retrofit: Retrofit) = retrofit.create(WebService::class.java)

    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context): FavoriteDataBase = Room.databaseBuilder(context, FavoriteDataBase::class.java, "table_favorites").build()

    @Singleton
    @Provides
    fun provideFavoritesDao(db: FavoriteDataBase) = db.favoriteDAO()
}