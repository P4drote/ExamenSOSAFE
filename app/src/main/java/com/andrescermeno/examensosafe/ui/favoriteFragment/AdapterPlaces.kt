package com.andrescermeno.examensosafe.ui.favoriteFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrescermeno.examensosafe.R
import com.andrescermeno.examensosafe.data.model.FavoritesEntity
import com.andrescermeno.examensosafe.ui.base.BaseViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_places.view.*
import java.lang.IllegalArgumentException

/**
 * Creada por Andrés Cermeño el 2/9/2020
 */
class AdapterPlaces (private var context: Context, private var listPlaces: List<FavoritesEntity>,
                     private val itemClickListener: OnFavoriteClickListener
): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnFavoriteClickListener{
        fun onItemClick(placeID: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return FavoritesViewHolder(LayoutInflater.from(context).inflate(R.layout.list_places, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is FavoritesViewHolder -> holder.bind(listPlaces[position], position)
            else -> throw IllegalArgumentException("El viewHolder no es el adecuado")
        }
    }

    override fun getItemCount(): Int = listPlaces.size

    inner class FavoritesViewHolder(itemView: View): BaseViewHolder<FavoritesEntity>(itemView){
        override fun bind(item: FavoritesEntity, position: Int) {
            itemView.setOnClickListener{ itemClickListener.onItemClick(item.placeID)}
            Glide.with(context).load(item.picture).into(itemView.img_referentialFavorite)
            itemView.tv_nameFavoritePlace.text = item.placeName
            itemView.tv_directionFavoritePlace.text = item.direction
        }

    }

}