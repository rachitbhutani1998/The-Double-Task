package com.example.thedoubletask.gallery

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thedoubletask.gallery.data.model.MyImage

class GalleryAdapter : PagingDataAdapter<MyImage, RecyclerView.ViewHolder>(MyImageCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyImageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is MyImageViewHolder -> holder.bind(getItem(position))
        }
    }
}

class MyImageCallback: DiffUtil.ItemCallback<MyImage> () {
    override fun areItemsTheSame(oldItem: MyImage, newItem: MyImage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MyImage, newItem: MyImage): Boolean {
        return oldItem == newItem
    }

}