package com.yuriishcherbyna.moviessho.ui.theme.details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yuriishcherbyna.moviessho.model.Genre

@Composable
fun GenreChips(
    genres: List<Genre>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        genres.forEach { genre ->
            SuggestionChip(
                onClick = {},
                label = { Text(text = genre.name) },
                modifier = Modifier.height(28.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}