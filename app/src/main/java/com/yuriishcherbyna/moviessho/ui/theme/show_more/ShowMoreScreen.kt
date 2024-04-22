package com.yuriishcherbyna.moviessho.ui.theme.show_more

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.yuriishcherbyna.moviessho.R
import com.yuriishcherbyna.moviessho.model.Result
import com.yuriishcherbyna.moviessho.ui.theme.MoviesShoTheme
import com.yuriishcherbyna.moviessho.ui.theme.components.GridList
import com.yuriishcherbyna.moviessho.ui.theme.home.components.ErrorComponent
import com.yuriishcherbyna.moviessho.ui.theme.home.components.LoadingComponent
import com.yuriishcherbyna.moviessho.ui.theme.oswaldFontFamily
import kotlinx.coroutines.flow.flowOf

@Composable
fun ShowMoreScreen(
    movies: LazyPagingItems<Result>,
    title: String,
    onNavigateBackClicked: () -> Unit,
    onMovieClicked: (Int) -> Unit,
    onRetryClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            ShowMoreTopBar(
                title = title,
                onNavigateBackClicked = onNavigateBackClicked
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (movies.loadState.refresh) {
                is LoadState.Loading -> {
                    LoadingComponent(modifier = Modifier.align(Alignment.Center))
                }
                is LoadState.Error -> {
                    ErrorComponent(
                        onRetryClicked = onRetryClicked,
                        onSearchRetryClicked = {}
                    )
                }
                else -> {
                    GridList(
                        movies = movies,
                        onMovieClicked = onMovieClicked,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowMoreTopBar(
    title: String,
    onNavigateBackClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "$title Movies",
                fontFamily = oswaldFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBackClicked) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.arrow_back_icon)
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

@Preview(apiLevel = 33)
@Composable
fun ShowMoreScreenPreview() {
    val movies = List(10) {
        Result(
            genreIds = listOf(28),
            id = 4753 + it,
            posterPath = "/hu40Uxp9WtpL34jv3zyWLb5zEVY.jpg",
            title = "No Way Up",
            voteAverage = 6.3
        )
    }

    MoviesShoTheme {
        ShowMoreScreen(
            movies = flowOf(PagingData.from(movies)).collectAsLazyPagingItems(),
            title = "Popular",
            onNavigateBackClicked = {},
            onMovieClicked = {},
            onRetryClicked = {}
        )
    }
}