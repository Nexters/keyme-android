package com.keyme.data.remote.datasource

import android.accounts.NetworkErrorException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.keyme.data.remote.api.KeymeApi
import com.keyme.domain.entity.response.QuestionSolvedScore
import timber.log.Timber

class QuestionSolvedScoreListPagingSource(
    private val keymeApi: KeymeApi,
    private val questionId: String,
    private val ownerId: Int,
) : PagingSource<Int, QuestionSolvedScore>() {
    override fun getRefreshKey(state: PagingState<Int, QuestionSolvedScore>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey ?: anchorPage?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuestionSolvedScore> {
        return try {
            val cursor = params.key
            val limit = params.loadSize

            val response = keymeApi.getQuestionSolvedScoreList(
                cursor = cursor,
                id = questionId,
                limit = limit,
                ownerId = ownerId,
            )

            return if (response.code == "200") {
                LoadResult.Page(
                    data = response.data.results,
                    prevKey = null,
                    nextKey = kotlin.runCatching { response.data.results.last().id }.getOrNull(),
                )
            } else {
                LoadResult.Error(NetworkErrorException(response.message))
            }
        } catch (e: Exception) {
            Timber.e(e)
            LoadResult.Error(e)
        }
    }
}
