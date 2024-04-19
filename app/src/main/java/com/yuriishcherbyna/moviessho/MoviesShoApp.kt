package com.yuriishcherbyna.moviessho

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.yuriishcherbyna.moviessho.ui.theme.home.HomeScreen
import com.yuriishcherbyna.moviessho.ui.theme.home.HomeViewModel


@Composable
fun MoviesShoApp() {

    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route) {

            val homeViewModel: HomeViewModel = hiltViewModel()

            val homeUiState = homeViewModel.uiState.collectAsState()
            val searchedMovies = homeViewModel.searchedMovies.collectAsLazyPagingItems()

            HomeScreen(
                uiState = homeUiState.value,
                searchedMovies = searchedMovies,
                isSearchBarVisible = homeViewModel.isSearchBarVisible,
                searchQuery = homeViewModel.searchQuery,
                onSearchQueryChanged = homeViewModel::onSearchQueryChanged,
                onSearchBarVisibleToggle = homeViewModel::onSearchBarVisibleToggle,
                onSearchedClicked = homeViewModel::searchMovies,
                onRetryClicked = {homeViewModel.getPopularAndNowShowingMovies()},
                onSearchRetryClicked = homeViewModel::searchMovies,
                onClearSearchedListUiState = homeViewModel::clearSearchedListUiState,
                onMovieClicked = {},
                onSeeAllClicked = {}
            )
        }
    }
}