package com.andrescermeno.examensosafe.di

import com.andrescermeno.examensosafe.domain.Repo
import com.andrescermeno.examensosafe.domain.RepoImpl
import com.andrescermeno.examensosafe.remote.DataSourceWeb
import com.andrescermeno.examensosafe.remote.DataSourceWebImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Creada por Andrés Cermeño el 1/9/2020
 */

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepoImpl(repoImpl: RepoImpl): Repo

    @Binds
    abstract fun bindDataSourceImpl(dataSourceWebImp: DataSourceWebImp):DataSourceWeb
}