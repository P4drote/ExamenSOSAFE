package com.andrescermeno.examensosafe.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Creada por Andrés Cermeño el 2/9/2020
 */
abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T, position: Int)
}