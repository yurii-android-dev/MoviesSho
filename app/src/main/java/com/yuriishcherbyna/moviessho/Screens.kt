package com.yuriishcherbyna.moviessho

import com.yuriishcherbyna.moviessho.util.Constants.MOVIE_ID_ARGUMENT
import com.yuriishcherbyna.moviessho.util.Constants.MOVIE_TYPE_ARGUMENT

sealed class Screens(val route: String) {
    data object Home: Screens("home_screen")
    data object ShowMore: Screens("show_more_screen/{$MOVIE_TYPE_ARGUMENT}") {
        fun passMovieType(movieType: String) = "show_more_screen/$movieType"
    }
    data object Details: Screens("details_screen/{$MOVIE_ID_ARGUMENT}") {
        fun passMovieId(movieId: Int) = "details_screen/$movieId"
    }
}