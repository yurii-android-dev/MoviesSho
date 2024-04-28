package com.yuriishcherbyna.moviessho.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yuriishcherbyna.moviessho.data.MoviesApi
import com.yuriishcherbyna.moviessho.data.paging.NowShowingPagingSource
import com.yuriishcherbyna.moviessho.data.paging.PopularPagingSource
import com.yuriishcherbyna.moviessho.data.paging.SearchPagingSource
import com.yuriishcherbyna.moviessho.model.Cast
import com.yuriishcherbyna.moviessho.model.MovieDetails
import com.yuriishcherbyna.moviessho.model.Result
import com.yuriishcherbyna.moviessho.model.VideoDetails
import com.yuriishcherbyna.moviessho.util.Constants.ITEMS_PER_PAGE
import com.yuriishcherbyna.moviessho.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MoviesApi
): MoviesRepository {

    override suspend fun getNowShowingMovies(): Flow<Resource<List<Result>>> {
        return flow {
            try {
                val popularMovies = api.getNowShowingMovies().results
                emit(Resource.Success(popularMovies))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error(
                    "Couldn't reach server. Check your internet connection."
                ))
            }
        }
    }

    override suspend fun getPopularMovies(): Flow<Resource<List<Result>>> {
        return flow {
            try {
                val nowShowingMovies = api.getPopularMovies().results
                emit(Resource.Success(nowShowingMovies))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error(
                    "Couldn't reach server. Check your internet connection."
                ))
            }
        }
    }

    override suspend fun getPaginatedNowShowingMovies(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                NowShowingPagingSource(api = api)
            }
        ).flow
    }

    override suspend fun getPaginatedPopularMovies(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                PopularPagingSource(api = api)
            }
        ).flow
    }

    override suspend fun searchMovies(query: String): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(query = query, api = api)
            }
        ).flow
    }

    override suspend fun getMovieDetails(movieId: Int): Resource<MovieDetails> {
        return try {
            val result = api.movieDetails(movieId)
            Resource.Success(result)
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server. Check your internet connection.")
        }
    }

    override suspend fun getMovieCasts(movieId: Int): Resource<List<Cast>> {
        return try {
            val result = api.getMovieCasts(movieId).cast
            Resource.Success(result)
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server. Check your internet connection.")
        }
    }

    override suspend fun getMovieTrailer(movieId: Int): Resource<List<VideoDetails>> {
        return try {
            val result = api.getMovieTrailer(movieId).results
            Resource.Success(result)
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server. Check your internet connection.")
        }
    }
}