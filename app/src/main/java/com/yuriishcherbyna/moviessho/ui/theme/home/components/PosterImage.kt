package com.yuriishcherbyna.moviessho.ui.theme.home.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yuriishcherbyna.moviessho.R
import com.yuriishcherbyna.moviessho.util.Constants.POSTER_IMAGE_BASE_URL

@Composable
fun PosterIMage(
    posterPath: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(POSTER_IMAGE_BASE_URL + posterPath)
            .crossfade(500)
            .build(),
        contentDescription = stringResource(R.string.poster_image),
        placeholder = painterResource(id = R.drawable.ic_placeholder),
        error = painterResource(id = R.drawable.ic_placeholder),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(RoundedCornerShape(20f))
    )
}