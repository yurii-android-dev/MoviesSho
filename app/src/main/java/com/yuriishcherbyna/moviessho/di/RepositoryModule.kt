package com.yuriishcherbyna.moviessho.di

import com.yuriishcherbyna.moviessho.data.MoviesApi
import com.yuriishcherbyna.moviessho.data.repo.MoviesRepository
import com.yuriishcherbyna.moviessho.data.repo.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(api: MoviesApi): MoviesRepository {
        return MoviesRepositoryImpl(api)
    }

}