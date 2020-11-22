package com.example.paint.view

import android.app.Dialog
import android.content.Context
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.paint.common.AppUtils
import com.example.paint.databinding.ColorPickerDialogBinding

/**
 * Custom color picker dialog
 * @param context
 */
class ColorPickerDialog(context: Context) : Dialog(context) {
    private lateinit var buttons: MutableList<ImageButton>
    private var myColorListener: ColorListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ColorPickerDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttons = mutableListOf()
        buttons.add(binding.colorBlue)
        buttons.add(binding.colorRed)
        buttons.add(binding.colorGreen)
        buttons.add(binding.colorPurple)
        buttons.add(binding.colorTeal)
        buttons.add(binding.colorBlack)
        buttons.add(binding.colorOrange)
        buttons.add(binding.colorYellow)

        customizeButtons()
        setListeners()
    }

    /**
     * Customize the buttons in rounded shape
     */
    private fun customizeButtons() {
        for (i in buttons.indices) {
            val d = ShapeDrawable(OvalShape())
            d.setBounds(50, 50, 50, 50)
            d.paint.style = Paint.Style.FILL
            d.paint.color = AppUtils.getColors(context)[i]
            buttons[i].background = d
        }
    }

    private fun setListeners() {
        for (i in buttons.indices) {
            buttons[i].tag = AppUtils.getColors(context)[i]
            buttons[i].setOnClickListener(listener)
        }
    }

    fun setColorListener(listener: ColorListener?) {
        myColorListener = listener
    }

    private val listener = View.OnClickListener { v ->
        myColorListener?.onColorClick(v, v.tag as Int)
        dismiss()
    }

    /**
     * interface for onclick color button in Dialog
     */
    interface ColorListener {
        fun onColorClick(v: View?, color: Int)
    }
}