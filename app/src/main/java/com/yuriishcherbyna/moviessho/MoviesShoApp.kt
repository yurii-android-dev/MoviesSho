package com.yuriishcherbyna.moviessho

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.yuriishcherbyna.moviessho.ui.theme.home.HomeScreen
import com.yuriishcherbyna.moviessho.ui.theme.home.HomeViewModel
import com.yuriishcherbyna.moviessho.ui.theme.home.MovieType
import com.yuriishcherbyna.moviessho.ui.theme.show_more.ShowMoreScreen
import com.yuriishcherbyna.moviessho.ui.theme.show_more.ShowMoreViewModel
import com.yuriishcherbyna.moviessho.util.Constants.MOVIE_TYPE_ARGUMENT


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
                onSeeAllClicked = { movieType ->
                    navController.navigate(Screens.ShowMore.passMovieType(movieType.name))
                }
            )
        }
        composable(
            route = Screens.ShowMore.route,
            arguments = listOf(navArgument(MOVIE_TYPE_ARGUMENT) {
                type = NavType.StringType
            })
        ) {

            val showMoreViewModel: ShowMoreViewModel = hiltViewModel()
            val movies = showMoreViewModel.movies.collectAsLazyPagingItems()

            val movieTypeString = it.arguments?.getString(MOVIE_TYPE_ARGUMENT)
            val movieType = MovieType.valueOf(movieTypeString ?: MovieType.POPULAR.name)

            ShowMoreScreen(
                movies = movies,
                title = movieType.title,
                onNavigateBackClicked = {
                    navController.navigateUp()
                },
                onMovieClicked = {},
                onRetryClicked = {
                    showMoreViewModel.getMoviesByType()
                }
            )
        }
    }
}