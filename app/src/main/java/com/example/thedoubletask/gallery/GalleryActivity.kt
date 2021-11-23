package com.example.thedoubletask.gallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thedoubletask.databinding.ActivityGalleryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {

    companion object {
        private const val GRID_SPAN = 2
    }

    private lateinit var binding: ActivityGalleryBinding
    private lateinit var viewModel: GalleryViewModel

    private val mAdapter = GalleryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GalleryViewModel::class.java]
        setupRecyclerView()
        setupImages()
        viewModel.getImages()
    }

    private fun setupImages() {
        lifecycleScope.launch {
            viewModel.getImages().collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvGallery.apply {
            layoutManager = GridLayoutManager(this@GalleryActivity, GRID_SPAN)
            adapter = mAdapter
        }
    }
}