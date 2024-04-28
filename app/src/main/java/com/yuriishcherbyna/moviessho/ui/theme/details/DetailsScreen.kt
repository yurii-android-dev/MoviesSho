package com.yuriishcherbyna.moviessho.ui.theme.details

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.yuriishcherbyna.moviessho.R
import com.yuriishcherbyna.moviessho.model.Cast
import com.yuriishcherbyna.moviessho.model.Genre
import com.yuriishcherbyna.moviessho.model.MovieDetails
import com.yuriishcherbyna.moviessho.ui.theme.MoviesShoTheme
import com.yuriishcherbyna.moviessho.ui.theme.components.ErrorComponent
import com.yuriishcherbyna.moviessho.ui.theme.components.ImdbRating
import com.yuriishcherbyna.moviessho.ui.theme.components.LoadingComponent
import com.yuriishcherbyna.moviessho.ui.theme.details.components.CastsList
import com.yuriishcherbyna.moviessho.ui.theme.details.components.DescriptionTextInfo
import com.yuriishcherbyna.moviessho.ui.theme.details.components.GenreChips
import com.yuriishcherbyna.moviessho.ui.theme.details.components.UsefulInfo
import com.yuriishcherbyna.moviessho.util.Constants.IMAGE_BASE_URL
import com.yuriishcherbyna.moviessho.util.Constants.YOUTUBE_BASE_URL
import com.yuriishcherbyna.moviessho.util.toPrettyFormattedTime
import kotlinx.coroutines.launch


@Composable
fun DetailsScreen(
    uiState: DetailsUiState,
    onNavigateBackClicked: () -> Unit,
    onRetryClicked:() -> Unit
) {

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = uiState.error) {
        uiState.error?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when  {
                uiState.isLoading -> {
                    LoadingComponent(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                uiState.error != null -> {
                    ErrorComponent(
                        canNavigateBack = true,
                        onNavigateBackClicked = onNavigateBackClicked,
                        onRetryClicked = onRetryClicked,
                        onSearchRetryClicked = {}
                    )
                }
                else -> {
                    DetailsBody(
                        uiState = uiState,
                        onNavigateBackClicked = onNavigateBackClicked,
                        onOpenTrailerClicked = {
                            if (uiState.youtubeKey != null) {
                                openMovieTrailer(uiState.youtubeKey, context)
                            }  else {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        context.getString(R.string.no_trailer_message),
                                        withDismissAction = true,
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailsBody(
    uiState: DetailsUiState,
    onNavigateBackClicked: () -> Unit,
    onOpenTrailerClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box {
            OpenTrailerComponent(
                backdropPath = uiState.movieDetails?.backdropPath ?: "",
                onNavigateBackClicked = onNavigateBackClicked,
                onOpenTrailerClicked = onOpenTrailerClicked
            )
            DescriptionComponent(
                casts = uiState.casts,
                movie = uiState.movieDetails!!,
                modifier = Modifier.padding(top = 210.dp)
            )
        }
    }
}

@Composable
fun OpenTrailerComponent(
    backdropPath: String,
    onNavigateBackClicked: () -> Unit,
    onOpenTrailerClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = IMAGE_BASE_URL + backdropPath,
            contentDescription = stringResource(R.string.backdrop_image),
            error = painterResource(id = R.drawable.ic_placeholder),
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )
        IconButton(onClick = onNavigateBackClicked) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.arrow_back_icon),
                tint = Color.White
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { onOpenTrailerClicked() },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.play_button),
                contentDescription = stringResource(R.string.play_button_icon),
                tint = Color.Unspecified,
                modifier = Modifier.size(42.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Play Trailer",
                color = Color.White
            )
        }
    }
}

@Composable
fun DescriptionComponent(
    casts: List<Cast>,
    movie: MovieDetails,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = movie.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 28.sp,
                maxLines = 2,
                modifier = Modifier.width(250.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            ImdbRating(voteAverage = movie.voteAverage)
            Spacer(modifier = Modifier.height(16.dp))
            GenreChips(genres = movie.genres)
            Spacer(modifier = Modifier.height(16.dp))
            UsefulInfo(
                runtime = movie.runtime.toPrettyFormattedTime(),
                language = movie.originalLanguage,
                year = movie.releaseDate.substring(0, 4)
            )
            Spacer(modifier = Modifier.height(16.dp))
            DescriptionTextInfo(description = movie.overview)
            Spacer(modifier = Modifier.height(16.dp))
            CastsList(casts = casts)
        }
    }
}

private fun openMovieTrailer(
    key: String,
    context: Context
) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_BASE_URL + key))
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        throw e
    }
}

@Preview(apiLevel = 33, showBackground = true)
@Composable
fun DetailsScreenPreview() {
    val uiState = DetailsUiState(
        casts = List(10) {
            Cast(
                id = "${54 + it}",
                name = "Timothée Chalamet",
                ""
            )
        },
        movieDetails = MovieDetails(
            backdropPath = "",
            genres = listOf(Genre(1, "Drama"), Genre(2, "Comedy")),
            id = 8472,
            originalLanguage = "en",
            overview = "Follow the mythic journey of Paul Atreides as he unites with Chani and the Fremen while on a path of revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the known universe, Paul endeavors to prevent a terrible future only he can foresee.",
            releaseDate = "2024-02-27",
            runtime = 138,
            title = "Spiderman: Now Way Home",
            voteAverage = 7.5,
            voteCount = 194
        )
    )
    MoviesShoTheme {
        DetailsScreen(
            uiState = uiState,
            onNavigateBackClicked = {},
            onRetryClicked = {}
        )
    }
}