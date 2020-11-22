package com.example.paint

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.paint.view.ColorPickerDialog
import com.example.paint.view.ColorPickerDialog.ColorListener
import com.example.paint.view.DrawingView

/**
 * Main activity for drawing
 */
class MainActivity : AppCompatActivity() {

    private val drawingView by lazy {
        DrawingView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(drawingView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.color -> {
                ColorPickerDialog(this).also {
                    it.setColorListener(object : ColorListener {
                        override fun onColorClick(v: View?, color: Int) {
                            drawingView.setBrushColor(color)
                        }
                    })
                    it.show()
                }
            }
            R.id.clear -> {
                drawingView.clearDrawing()
            }
            else -> { println("no matches!") }
        }
        return super.onOptionsItemSelected(item)
    }
}