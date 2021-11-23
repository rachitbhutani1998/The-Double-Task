package com.example.thedoubletask.gallery.data.repository

import androidx.paging.*
import com.example.thedoubletask.gallery.data.model.MyImage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryRepository
@Inject
constructor(private val dataSource: ImagePagingSource) {

    companion object {
        private const val PAGE_SIZE = 20
    }

    fun getImages(): Flow<PagingData<MyImage>> {
        return Pager(config = PagingConfig(PAGE_SIZE), pagingSourceFactory = {
            dataSource
        }).flow
    }

}