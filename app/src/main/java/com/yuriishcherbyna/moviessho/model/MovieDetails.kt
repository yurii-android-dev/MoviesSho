package com.yuriishcherbyna.moviessho.model

import com.squareup.moshi.Json

data class MovieDetails(
    @Json(name = "backdrop_path") val backdropPath: String?,
    val genres: List<Genre>,
    val id: Int,
    @Json(name = "original_language") val originalLanguage: String,
    val overview: String,
    @Json(name = "release_date") val releaseDate: String,
    val runtime: Int,
    val title: String,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int
)