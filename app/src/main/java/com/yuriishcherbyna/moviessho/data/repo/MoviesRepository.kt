package com.yuriishcherbyna.moviessho.data.repo

import com.yuriishcherbyna.moviessho.model.Result
import com.yuriishcherbyna.moviessho.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getNowShowingMovies(): Flow<Resource<List<Result>>>

    suspend fun getNowPopularMovies(): Flow<Resource<List<Result>>>

    suspend fun searchMovies(): Flow<Resource<List<Result>>>

}