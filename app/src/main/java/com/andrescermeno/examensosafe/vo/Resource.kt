package com.andrescermeno.examensosafe.vo

import java.lang.Exception

/**
 * Creada por Andrés Cermeño el 31/8/2020
 */
sealed class  Resource<out T> {
    class Loading<out T>: Resource<T>()
    data class Success<out T>(val data: T): Resource<T>()
    data class Failure<out T>(val exception: Exception): Resource<T>()
}