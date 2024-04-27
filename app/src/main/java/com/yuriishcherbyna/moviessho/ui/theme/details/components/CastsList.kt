package com.yuriishcherbyna.moviessho.ui.theme.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuriishcherbyna.moviessho.model.Cast
import com.yuriishcherbyna.moviessho.ui.theme.oswaldFontFamily

@Composable
fun CastsList(
    casts: List<Cast>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Cast",
            fontFamily = oswaldFontFamily,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = casts,
                key = { cast ->
                    cast.id
                }
            ) {castResult ->
                CastItem(cast = castResult)
            }
        }
    }
}