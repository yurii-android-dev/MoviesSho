package com.yuriishcherbyna.moviessho.ui.theme.show_more

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yuriishcherbyna.moviessho.data.repo.MoviesRepository
import com.yuriishcherbyna.moviessho.model.Result
import com.yuriishcherbyna.moviessho.ui.theme.home.MovieType
import com.yuriishcherbyna.moviessho.util.Constants.MOVIE_TYPE_ARGUMENT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowMoreViewModel @Inject constructor(
    private val repository: MoviesRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val movieType = savedStateHandle.get<String>(MOVIE_TYPE_ARGUMENT) ?: ""

    private val _movies = MutableStateFlow<PagingData<Result>>(PagingData.empty())
    val movies = _movies.asStateFlow()

    init {
        getMoviesByType()
    }

    fun getMoviesByType() {
        viewModelScope.launch {
            if (movieType == MovieType.POPULAR.name) {
                repository.getPaginatedPopularMovies().cachedIn(viewModelScope)
                    .collect { popularMovies ->
                        _movies.value = popularMovies
                }
            } else {
                repository.getPaginatedNowShowingMovies().cachedIn(viewModelScope)
                    .collect { nowShowingMovies ->
                        _movies.value = nowShowingMovies
                    }
            }
        }
    }



}