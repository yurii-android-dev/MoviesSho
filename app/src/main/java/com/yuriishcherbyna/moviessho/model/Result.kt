package com.yuriishcherbyna.moviessho.model

import com.squareup.moshi.Json

data class Result(
    @Json(name = "genre_ids") val genreIds: List<Int>,
    val id: Int,
    @Json(name = "poster_path") val posterPath: String?,
    val title: String,
    @Json(name = "vote_average") val voteAverage: Double,
)