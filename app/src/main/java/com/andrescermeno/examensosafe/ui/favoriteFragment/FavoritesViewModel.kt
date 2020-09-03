package com.andrescermeno.examensosafe.ui.favoriteFragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.andrescermeno.examensosafe.common.ScopedViewModel
import com.andrescermeno.examensosafe.data.model.FavoritesEntity
import com.andrescermeno.examensosafe.domain.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Creada por Andrés Cermeño el 2/9/2020
 */

class FavoritesViewModel @ViewModelInject constructor(private var repo: Repo) : ScopedViewModel() {

    private val _listFavorites = MutableLiveData<List<FavoritesEntity>>()
    val listFavorites: LiveData<List<FavoritesEntity>> get() = _listFavorites

    init {
        initScope()

    }

    fun initializedRecycler() {
        launch {
            _listFavorites.value = repo.getAllFavoritesPlace()
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}