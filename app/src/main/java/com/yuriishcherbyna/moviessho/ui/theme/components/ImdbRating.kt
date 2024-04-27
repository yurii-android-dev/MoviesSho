package com.yuriishcherbyna.moviessho.ui.theme.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yuriishcherbyna.moviessho.R
import kotlin.math.round

@Composable
fun ImdbRating(
    voteAverage: Double
) {

    val roundedVoteAverage = round(voteAverage * 10) / 10f

    Row {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = stringResource(R.string.star_icon),
            tint = Color(255,195,26)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(
                id = R.string.imdb_rating,
                roundedVoteAverage
            ),
            fontWeight = FontWeight.Light
        )
    }
}