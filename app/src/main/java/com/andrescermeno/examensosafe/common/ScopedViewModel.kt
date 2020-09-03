package com.andrescermeno.examensosafe.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel

/**
 * Creada por Andrés Cermeño el 2/8/2020
 */
abstract class ScopedViewModel : ViewModel(), Scope by Scope.Impl() {

    init {
        initScope()
    }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}