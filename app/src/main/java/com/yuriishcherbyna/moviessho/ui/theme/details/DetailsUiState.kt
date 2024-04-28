package com.yuriishcherbyna.moviessho.ui.theme.details

import com.yuriishcherbyna.moviessho.model.Cast
import com.yuriishcherbyna.moviessho.model.MovieDetails

data class DetailsUiState(
    val casts: List<Cast> = emptyList(),
    val movieDetails: MovieDetails? = null,
    val youtubeKey: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
