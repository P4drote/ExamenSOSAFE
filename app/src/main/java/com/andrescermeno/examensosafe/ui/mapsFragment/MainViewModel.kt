package com.andrescermeno.examensosafe.ui.mapsFragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.andrescermeno.examensosafe.common.ScopedViewModel
import com.andrescermeno.examensosafe.domain.Repo
import kotlinx.coroutines.Dispatchers

/**
 * Creada por Andrés Cermeño el 31/8/2020
 */

class MainViewModel @ViewModelInject constructor (private val repo: Repo):ScopedViewModel() {


    val wordToSearch = MutableLiveData<String>()
    val firstLocation = MutableLiveData<String>()

    init {
        initScope()
    }

    val locationsList = wordToSearch.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO){
                emit(repo.getNearbyPlaces(firstLocation.value.toString(), "2000", wordToSearch.value.toString(), "AIzaSyAh_bAfEqdromedJ2yOECCzkggzolKoI4E"))
        }
    }

    fun clickAirPort(){
        wordToSearch.value = "Aeropuerto"
    }

    fun clickRestaurant(){
        wordToSearch.value = "Restaurante"
    }

    fun clickFuelStation(){
        wordToSearch.value = "Gasolinera"
    }

    fun clickGroceries(){
        wordToSearch.value = "Supermercado"
    }

    fun clickCoffeeShop(){
        wordToSearch.value = "Cafetería"
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}