package com.yuriishcherbyna.moviessho.ui.theme.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriishcherbyna.moviessho.data.repo.MoviesRepository
import com.yuriishcherbyna.moviessho.util.Constants.MOVIE_ID_ARGUMENT
import com.yuriishcherbyna.moviessho.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle[MOVIE_ID_ARGUMENT])

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getFullMovieDetails()
    }

    fun getFullMovieDetails() {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(isLoading = true) }
            val movieDetailsDeferred = async { moviesRepository.getMovieDetails(movieId) }
            val movieCastsDeferred = async { moviesRepository.getMovieCasts(movieId) }
            val movieTrailerDeferred = async { moviesRepository.getMovieTrailer(movieId) }

            val movieDetails = movieDetailsDeferred.await()
            val movieCasts = movieCastsDeferred.await()
            val movieTrailer = movieTrailerDeferred.await()

            when (movieDetails) {
                is Resource.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            movieDetails = movieDetails.data,
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            error = movieDetails.message,
                            isLoading = false
                        )
                    }
                }
            }

            when (movieCasts) {
                is Resource.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            casts = movieCasts.data ?: _uiState.value.casts,
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            error = movieDetails.message,
                            isLoading = false
                        )
                    }
                }
            }

            when (movieTrailer) {
                is Resource.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            youtubeKey = if (movieTrailer.data?.isNotEmpty() == true) {
                                movieTrailer.data.first().key
                            } else {
                                 _uiState.value.youtubeKey
                            },
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            error = movieDetails.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

}