package com.example.thedoubletask.gallery.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.thedoubletask.gallery.data.model.MyImage
import com.example.thedoubletask.gallery.data.service.GalleryService
import java.lang.Exception
import javax.inject.Inject

class ImagePagingSource @Inject constructor(val mService: GalleryService) : PagingSource<Int, MyImage>() {

    companion object {
        private const val ORDER_TYPE = "popularity"
    }

    override fun getRefreshKey(state: PagingState<Int, MyImage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyImage> {
        val currentPage = params.key ?: 1
        return try {
            val response = mService.getImages(currentPage, 20, ORDER_TYPE)
            LoadResult.Page(data = response, prevKey = if (currentPage == 1) null else currentPage, nextKey = currentPage + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}