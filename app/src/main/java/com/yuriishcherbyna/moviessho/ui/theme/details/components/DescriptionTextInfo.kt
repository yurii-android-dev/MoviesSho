package com.yuriishcherbyna.moviessho.ui.theme.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuriishcherbyna.moviessho.R
import com.yuriishcherbyna.moviessho.ui.theme.oswaldFontFamily

@Composable
fun DescriptionTextInfo(
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.description),
            fontFamily = oswaldFontFamily,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description,
            lineHeight = 26.sp,
            fontWeight = FontWeight.Light
        )
    }
}