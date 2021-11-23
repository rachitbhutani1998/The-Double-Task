package com.example.thedoubletask.shapes

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.thedoubletask.R
import com.example.thedoubletask.databinding.ActivityShapesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShapesActivity : AppCompatActivity() {

    companion object {
        private const val BORDER_THRESHOLD = 100
    }

    private lateinit var binding: ActivityShapesBinding

    private val views = mutableListOf<ShapeView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShapesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupShapeButtons()
        setupUndoButton()
    }

    private fun setupUndoButton() {
        binding.btnUndo.setOnClickListener {
            removeLastView()
        }
    }

    private fun setupShapeButtons() {
        binding.btnCircle.setOnClickListener {
            addShape(ShapeView.CIRCLE)
        }
        binding.btnSquare.setOnClickListener {
            addShape(ShapeView.SQUARE)
        }
    }

    private fun addShape(shape: Int) {

        val rlBottom = binding.rlShapes.bottom
        val rlLeft = binding.rlShapes.left
        val rlRight = binding.rlShapes.right
        val rlTop = binding.rlShapes.top

        val newShape = ShapeView(this).apply {
            shapeColor = Color.RED
            this.shape = shape
            val newX = ((rlLeft + BORDER_THRESHOLD)..(rlRight - BORDER_THRESHOLD)).random().toFloat()
            val newY = ((rlTop + BORDER_THRESHOLD)..(rlBottom - BORDER_THRESHOLD)).random().toFloat()
            x = newX
            y = newY
        }
        views.add(newShape)
        binding.rlShapes.addView(newShape)
    }

    private fun removeLastView() {
        if (views.size > 0) {
            binding.rlShapes.removeView(views.last())
            views.removeAt(views.size - 1)
        } else Toast.makeText(this, "Add a shape bro!!!", Toast.LENGTH_SHORT).show()
    }

}