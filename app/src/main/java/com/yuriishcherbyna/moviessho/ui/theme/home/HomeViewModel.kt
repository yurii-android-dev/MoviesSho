package com.yuriishcherbyna.moviessho.ui.theme.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.yuriishcherbyna.moviessho.model.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {

    var searchQuery by mutableStateOf("")
        private set
    var isSearchBarVisible by mutableStateOf(false)
        private set

    private val _popularMovies = MutableStateFlow<List<Result>>(emptyList())
    val popularMovies = _popularMovies.asStateFlow()
    private val _nowShowingMovies = MutableStateFlow<List<Result>>(emptyList())
    val nowShowingMovies = _nowShowingMovies.asStateFlow()

    fun onSearchQueryChanged(query: String) {
        searchQuery = query
    }
    fun onSearchBarVisibleToggle() {
        isSearchBarVisible = !isSearchBarVisible
    }
}