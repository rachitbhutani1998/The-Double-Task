package com.example.thedoubletask.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.thedoubletask.gallery.data.model.MyImage
import com.example.thedoubletask.gallery.data.repository.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel
@Inject constructor(private val galleryRepository: GalleryRepository) : ViewModel() {

    fun getImages(): Flow<PagingData<MyImage>> {
        return galleryRepository.getImages().cachedIn(viewModelScope)
    }

}