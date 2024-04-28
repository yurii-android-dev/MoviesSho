package com.yuriishcherbyna.moviessho.ui.theme.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UsefulInfo(
    runtime: String,
    language: String,
    year: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        UsefulInfoItem(
            title = "Length",
            value = runtime
        )
        UsefulInfoItem(
            title = "Language",
            value = language
        )
        UsefulInfoItem(
            title = "Year",
            value = year
        )
    }
}