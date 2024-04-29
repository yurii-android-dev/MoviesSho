package com.yuriishcherbyna.moviessho.ui.theme.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yuriishcherbyna.moviessho.R
import com.yuriishcherbyna.moviessho.model.Cast
import com.yuriishcherbyna.moviessho.util.Constants.CAST_IMAGE_BASE_URL

@Composable
fun CastItem(
    cast: Cast,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.width(100.dp)
    ) {
        AsyncImage(
            model = CAST_IMAGE_BASE_URL + cast.profilePath,
            contentDescription = stringResource(R.string.profile_image),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            error = painterResource(id = R.drawable.ic_placeholder),
            modifier = Modifier
                .height(100.dp)
                .clip(RoundedCornerShape(8.dp)),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = cast.name,
            maxLines = 2,
        )
    }
}