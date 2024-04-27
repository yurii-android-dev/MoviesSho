package com.yuriishcherbyna.moviessho.ui.theme.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuriishcherbyna.moviessho.model.Result
import com.yuriishcherbyna.moviessho.ui.theme.components.ImdbRating

@Composable
fun NowShowingItem(
    movie: Result,
    onMovieClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(150.dp)
            .clickable { onMovieClicked(movie.id) }
    ) {
        PosterImage(
            posterPath = movie.posterPath ?: "",
            modifier = Modifier
                .height(200.dp)
                .width(150.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.width(150.dp),
            text = movie.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(12.dp))
        ImdbRating(voteAverage = movie.voteAverage)
    }
}