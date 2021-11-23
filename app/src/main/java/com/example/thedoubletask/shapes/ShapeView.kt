package com.example.thedoubletask.shapes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.example.thedoubletask.R

class ShapeView : View {

    companion object {
        const val SQUARE = 0
        const val CIRCLE = 1
        const val SHAPE_SIZE = 100f
    }

    private var mShapeColor: Int = Color.RED
    private var mShape: Int = CIRCLE
    private val mPaint: Paint = Paint()

    var shapeColor: Int
        get() = mShapeColor
        set(value) {
            mShapeColor = value
            invalidateTextPaintAndMeasurements()
        }

    var shape: Int
        get() = mShape
        set(value) {
            mShape = value
            invalidateTextPaintAndMeasurements()
        }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.ShapeView, defStyle, 0
        )

        mShapeColor = a.getColor(R.styleable.ShapeView_shapeColor, shapeColor)
        mShape = a.getInt(R.styleable.ShapeView_shape, CIRCLE)
        a.recycle()

        mPaint.apply {
            flags = Paint.ANTI_ALIAS_FLAG
            isAntiAlias = true
            color = mShapeColor
            style = Paint.Style.FILL
        }
        invalidateTextPaintAndMeasurements()
    }

    private fun invalidateTextPaintAndMeasurements() {
        mPaint.color = shapeColor
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mShape == CIRCLE)
            canvas.drawCircle(SHAPE_SIZE/2, SHAPE_SIZE/2, SHAPE_SIZE/2, mPaint)
        else
            canvas.drawRect(0f, 0f, SHAPE_SIZE, SHAPE_SIZE, mPaint)
    }
}