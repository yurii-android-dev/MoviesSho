package com.yuriishcherbyna.moviessho.ui.theme.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yuriishcherbyna.moviessho.model.Result
import com.yuriishcherbyna.moviessho.util.toGenresText

@Composable
fun PopularItem(
    movie: Result,
    onMovieClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollableChips = rememberScrollState()
    
    Row(
        modifier = modifier
            .fillMaxSize()
            .clickable { onMovieClicked(movie.id) }
    ) {
        PosterIMage(
            posterPath = movie.posterPath ?: "",
            modifier = Modifier
                .height(150.dp)
                .width(100.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = movie.title,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            ImdbRating(voteAverage = movie.voteAverage)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.horizontalScroll(scrollableChips)
            ) {
                movie.genreIds.toGenresText().take(3).forEach { genre ->
                    SuggestionChip(
                        onClick = {},
                        label = { Text(text = genre) },
                        modifier = Modifier.height(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}