package com.example.paint.view

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.paint.R
import com.example.paint.common.AppConstants

/**
 * Custom Canvas View implementation
 * @param context
 */
class DrawingView(context: Context?) : View(context) {
    private lateinit var canvas: Canvas
    private lateinit var bitmap: Bitmap
    private var path: Path = Path()
    private val canvasPaint = Paint(Paint.DITHER_FLAG)
    private val paint = Paint().apply {
        color = ResourcesCompat.getColor(resources, R.color.black, null)
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = AppConstants.STROKE_WIDTH
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap, 0f, 0f, canvasPaint)
        canvas?.drawPath(path, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
            }
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(event.x, event.y)
            }
            MotionEvent.ACTION_UP -> {
                canvas.drawPath(path, paint)
                path.reset()
            }
            else -> {
            }
        }
        invalidate()
        return true
    }

    /**
     * Set the new brush color
     * @param color
     */
    fun setBrushColor(color: Int) {
        invalidate()
        paint.color = color
    }

    /**
     * Clear the drawing area
     */
    fun clearDrawing() {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR)
        invalidate()
    }
}