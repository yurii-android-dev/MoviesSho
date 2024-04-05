package com.yuriishcherbyna.moviessho.data

import com.yuriishcherbyna.moviessho.BuildConfig
import com.yuriishcherbyna.moviessho.model.Movies
import com.yuriishcherbyna.moviessho.model.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("/movie/now_playing")
    suspend fun getNowShowingMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Movies

    @GET("/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Movies

    @GET("/search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Movies
}