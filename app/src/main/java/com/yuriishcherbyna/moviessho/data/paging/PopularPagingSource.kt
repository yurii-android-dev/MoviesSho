package com.yuriishcherbyna.moviessho.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yuriishcherbyna.moviessho.data.MoviesApi
import com.yuriishcherbyna.moviessho.model.Result
import retrofit2.HttpException
import java.io.IOException

class PopularPagingSource(
    private val api: MoviesApi
): PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val currentPage = params.key ?: 1

            val response = api.getPopularMovies(page = currentPage)
            val endOfPaginatedReached = response.results.isEmpty()

            LoadResult.Page(
                data = response.results,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (endOfPaginatedReached) null else currentPage + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}