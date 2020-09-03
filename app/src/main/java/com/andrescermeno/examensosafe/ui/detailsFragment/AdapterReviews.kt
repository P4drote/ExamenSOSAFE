package com.andrescermeno.examensosafe.ui.detailsFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrescermeno.examensosafe.R
import com.andrescermeno.examensosafe.remote.Review
import com.andrescermeno.examensosafe.ui.base.BaseViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_reviews.view.*
import java.lang.IllegalArgumentException

/**
 * Creada por Andrés Cermeño el 2/9/2020
 */
class AdapterReviews(private val context: Context, private val listReviews : List<Review>): RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return ReviewsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_reviews,parent,false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is ReviewsViewHolder -> holder.bind(listReviews[position], position)
            else -> throw IllegalArgumentException("El viewHolder no es el adecuado")
        }
    }

    override fun getItemCount(): Int = listReviews.size

    inner class ReviewsViewHolder(itemView: View): BaseViewHolder<Review>(itemView){
        override fun bind(item: Review, position: Int) {
            Glide.with(context).load(item.profile_photo_url).into(itemView.img_author)
            itemView.tv_author.text = item.author_name
            itemView.tv_relativeTime.text = item.relative_time_description
            itemView.tv_opinion.text = item.text
            itemView.ratingBarReviews.rating = item.rating.toFloat()
        }
    }

}
