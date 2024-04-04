package com.yuriishcherbyna.moviessho.ui.theme.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuriishcherbyna.moviessho.R
import com.yuriishcherbyna.moviessho.ui.theme.home.MovieType
import com.yuriishcherbyna.moviessho.ui.theme.oswaldFontFamily

@Composable
fun TextAndSeeAllButtonRow(
    text: Int,
    movieType: MovieType,
    onSeeAllClicked: (MovieType) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = text),
            fontFamily = oswaldFontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(
            onClick = { onSeeAllClicked(movieType) },
            contentPadding = PaddingValues(horizontal = 8.dp),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .height(32.dp)
        ) {
            Text(
                text = stringResource(id = R.string.see_more),
                fontSize = 12.sp
            )
        }
    }
}