package com.yuriishcherbyna.moviessho

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

            HomeScreen(
                uiState = homeUiState.value,
                isSearchBarVisible = homeViewModel.isSearchBarVisible,
                searchQuery = homeViewModel.searchQuery,
                onSearchQueryChanged = homeViewModel::onSearchQueryChanged,
                onSearchBarVisibleToggle = homeViewModel::onSearchBarVisibleToggle
            )
        }
    }
}