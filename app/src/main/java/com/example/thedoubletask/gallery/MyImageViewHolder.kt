package com.example.thedoubletask.gallery

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.thedoubletask.databinding.LayoutMyImageBinding
import com.example.thedoubletask.gallery.data.model.MyImage
import com.example.thedoubletask.gallery.data.model.User
import com.example.thedoubletask.util.loadImage

class MyImageViewHolder(private val binding: LayoutMyImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            MyImageViewHolder(LayoutMyImageBinding.inflate(LayoutInflater.from(parent.context)))
    }

    fun bind(myImage: MyImage?) {
        binding.ivImage.loadImage(myImage?.url?.thumb)
        binding.tvInsta.text = myImage?.user?.instagram?.let { "@$it" } ?: myImage?.user?.userName
        toggleDetails(false)
        binding.ivImage.setOnClickListener {
            toggleDetails(binding.ivInsta.isVisible.not())
        }
        binding.tvInsta.setOnClickListener {
            myImage?.user?.let { openUserProfile(it) }
        }
    }

    private fun toggleDetails(show: Boolean) {
        if (show) {
            binding.ivInsta.visibility = View.VISIBLE
            binding.tvInsta.visibility = View.VISIBLE
        } else {
            binding.ivInsta.visibility = View.GONE
            binding.tvInsta.visibility = View.GONE
        }
    }

    private fun openUserProfile(user: User) {
        (user.instagram?.let { "https://www.instagram.com/$it/" } ?: user.portfolio)?.let {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(it)
            binding.root.context.startActivity(intent)
        }
    }
}