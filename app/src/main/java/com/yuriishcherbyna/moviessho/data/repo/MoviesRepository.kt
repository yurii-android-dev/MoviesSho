package com.yuriishcherbyna.moviessho.data.repo

import androidx.paging.PagingData
import com.yuriishcherbyna.moviessho.model.Cast
import com.yuriishcherbyna.moviessho.model.MovieDetails
import com.yuriishcherbyna.moviessho.model.Result
import com.yuriishcherbyna.moviessho.model.VideoDetails
import com.yuriishcherbyna.moviessho.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getNowShowingMovies(): Flow<Resource<List<Result>>>

    suspend fun getPopularMovies(): Flow<Resource<List<Result>>>

    suspend fun getPaginatedNowShowingMovies(): Flow<PagingData<Result>>

    suspend fun getPaginatedPopularMovies(): Flow<PagingData<Result>>

    suspend fun searchMovies(query: String): Flow<PagingData<Result>>

    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetails>

    suspend fun getMovieCasts(movieId: Int): Resource<List<Cast>>

    suspend fun getMovieTrailer(movieId: Int): Resource<List<VideoDetails>>

}