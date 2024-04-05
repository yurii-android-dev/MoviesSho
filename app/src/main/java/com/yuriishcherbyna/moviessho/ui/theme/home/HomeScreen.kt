package com.yuriishcherbyna.moviessho.ui.theme.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuriishcherbyna.moviessho.R
import com.yuriishcherbyna.moviessho.model.Result
import com.yuriishcherbyna.moviessho.ui.theme.MoviesShoTheme
import com.yuriishcherbyna.moviessho.ui.theme.home.components.NowShowingItem
import com.yuriishcherbyna.moviessho.ui.theme.home.components.PopularItem
import com.yuriishcherbyna.moviessho.ui.theme.home.components.TextAndSeeAllButtonRow
import com.yuriishcherbyna.moviessho.ui.theme.oswaldFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    popularMovies: List<Result>,
    nowShowingMovies: List<Result>,
    isSearchBarVisible: Boolean,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchBarVisibleToggle: () -> Unit
) {

    Scaffold(
        topBar = {
            if (isSearchBarVisible) {
                SearchBar(
                    query = searchQuery,
                    onQueryChange = onSearchQueryChanged,
                    onSearch = {},
                    leadingIcon = {
                        IconButton(onClick = {
                            onSearchBarVisibleToggle()
                            onSearchQueryChanged("")
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = ""
                            )
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            if (searchQuery.isNotEmpty()) {
                                onSearchQueryChanged("")
                            } else {
                                onSearchBarVisibleToggle()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = ""
                            )
                        }
                    },
                    placeholder = {
                        Text(text = "Search movies")
                    },
                    active = isSearchBarVisible,
                    onActiveChange = {}
                ) {

                }
            }

            HomeTopAppBar(
                onSearchClicked = { onSearchBarVisibleToggle() }
            )
        }
    ) { innerPadding ->

        PopularAndNowShowingList(
            popularMovies = popularMovies,
            nowShowingMovies = nowShowingMovies,
            onMovieClicked = {},
            onSeeAllClicked = {},
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    onSearchClicked: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontFamily = oswaldFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 28.sp
            )
        },
        actions = {
            IconButton(
                onClick = onSearchClicked
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.icon_color)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}

@Composable
fun NowShowingList(
    movies: List<Result>,
    onMovieClicked: (Int) -> Unit,
    onSeeAllClicked: (MovieType) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TextAndSeeAllButtonRow(
            text = R.string.now_showing,
            movieType = MovieType.NOW_SHOWING,
            onSeeAllClicked = onSeeAllClicked,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = movies,
                key = { movie ->
                    movie.id
                }
            ) {movie ->
                NowShowingItem(
                    movie = movie,
                    onMovieClicked = onMovieClicked
                )
            }
        }
    }
}

@Composable
fun PopularAndNowShowingList(
    popularMovies: List<Result>,
    nowShowingMovies: List<Result>,
    onMovieClicked: (Int) -> Unit,
    onSeeAllClicked: (MovieType) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            Column {
                NowShowingList(
                    movies = nowShowingMovies,
                    onMovieClicked = onMovieClicked,
                    onSeeAllClicked = onSeeAllClicked
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextAndSeeAllButtonRow(
                    text = R.string.popular,
                    movieType = MovieType.POPULAR,
                    onSeeAllClicked = onSeeAllClicked,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }
        }

        items(
            items = popularMovies,
            key = { movie ->
                movie.id
            }
        ) {movieResult ->
            PopularItem(
                movie = movieResult,
                onMovieClicked = onMovieClicked,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}


@Preview(apiLevel = 33)
@Composable
fun HomeScreenPreview() {
    MoviesShoTheme {
        val movies = List(10) {
            Result(
                genreIds = listOf(28, 35, 27),
                id = 4753 + it,
                posterPath = "/hu40Uxp9WtpL34jv3zyWLb5zEVY.jpg",
                title = "No Way Up",
                voteAverage = 6.3
            )
        }
        HomeScreen(
            popularMovies = movies,
            nowShowingMovies = movies,
            isSearchBarVisible = false,
            searchQuery = "",
            onSearchQueryChanged = {},
            onSearchBarVisibleToggle = {}
        )
    }
}