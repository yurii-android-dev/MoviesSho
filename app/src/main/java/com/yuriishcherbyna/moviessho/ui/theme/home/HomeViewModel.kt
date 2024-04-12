package com.yuriishcherbyna.moviessho.ui.theme.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriishcherbyna.moviessho.data.repo.MoviesRepository
import com.yuriishcherbyna.moviessho.model.Result
import com.yuriishcherbyna.moviessho.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MoviesUiState(
    val nowShowingMovies: List<Result> = emptyList(),
    val popularMovies: List<Result> = emptyList(),
    val searchedMovies: List<Result> = emptyList(),
    val isLoading: Boolean = false,
    val isSearchLoading: Boolean = false,
    val error: String? = null,
    val searchError: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MoviesRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(MoviesUiState())
    val uiState = _uiState.asStateFlow()

    var searchQuery by mutableStateOf("")
        private set
    var isSearchBarVisible by mutableStateOf(false)
        private set

    init {
        getPopularAndNowShowingMovies()
    }

    fun onSearchQueryChanged(query: String) {
        searchQuery = query
    }
    fun onSearchBarVisibleToggle() {
        isSearchBarVisible = !isSearchBarVisible
    }

    fun getPopularAndNowShowingMovies() {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(isLoading = true) }
            val nowShowingMovies = repository.getNowShowingMovies()
                .shareIn(this, SharingStarted.WhileSubscribed())
            val popularMovies = repository.getPopularMovies()
                .shareIn(this, SharingStarted.WhileSubscribed())

            nowShowingMovies.combine(popularMovies) { nowShowing, popular ->
                Pair(nowShowing, popular)
            }.collect { (nowShowingResult, popularResult) ->
                _uiState.update { state ->
                    state.copy(
                        nowShowingMovies = (nowShowingResult as? Resource.Success)?.data
                            ?: _uiState.value.nowShowingMovies,
                        popularMovies = (popularResult as? Resource.Success)?.data
                            ?: _uiState.value.popularMovies,
                        isLoading = false,
                        error = nowShowingResult.takeIf { it is Resource.Error }?.message
                            ?: popularResult.takeIf { it is Resource.Error }?.message
                    )
                }
            }
        }
    }

    fun searchMovies() {
        if (searchQuery.isNotEmpty()) {
            viewModelScope.launch {
                _uiState.update { state -> state.copy(isSearchLoading = true) }
                repository.searchMovies(searchQuery).collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _uiState.update { state ->
                                state.copy(
                                    searchedMovies = resource.data ?: state.searchedMovies,
                                    isSearchLoading = false,
                                    searchError = null
                                )
                            }
                        }
                        is Resource.Error -> {
                            _uiState.update { state ->
                                state.copy(
                                    isSearchLoading = false,
                                    searchError = resource.message
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun clearSearchedListUiState() {
        _uiState.update { state ->
            state.copy(searchedMovies = emptyList())
        }
    }

}