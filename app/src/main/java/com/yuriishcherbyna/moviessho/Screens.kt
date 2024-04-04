package com.yuriishcherbyna.moviessho

sealed class Screens(val route: String) {
    data object Home: Screens("home_screen")
}