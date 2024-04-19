package com.yuriishcherbyna.moviessho.data.repo

import androidx.paging.PagingData
import com.yuriishcherbyna.moviessho.model.Result
import com.yuriishcherbyna.moviessho.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getNowShowingMovies(): Flow<Resource<List<Result>>>

    suspend fun getPopularMovies(): Flow<Resource<List<Result>>>

    suspend fun searchMovies(query: String): Flow<PagingData<Result>>

}