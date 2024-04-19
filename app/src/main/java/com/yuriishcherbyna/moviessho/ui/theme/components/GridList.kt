package com.yuriishcherbyna.moviessho.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.yuriishcherbyna.moviessho.model.Result
import com.yuriishcherbyna.moviessho.ui.theme.home.components.NowShowingItem

@Composable
fun GridList(
    movies: LazyPagingItems<Result>,
    onMovieClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.FixedSize(150.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(count = movies.itemCount) {index ->
            val item = movies[index]
            item?.let { movieResult ->
                NowShowingItem(
                    movie = movieResult,
                    onMovieClicked = onMovieClicked
                )
            }
        }
    }
}