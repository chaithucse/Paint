package com.example.paint.common

import android.content.Context
import com.example.paint.R

/**
 * Utility class
 */
class AppUtils {
    companion object {
        /**
         * List od colors to display in custom dialog
         * @param context
         */
        fun getColors(context: Context): List<Int> {
            val colors: MutableList<Int> = mutableListOf()
            colors.add(context.resources.getColor(R.color.blue, null))
            colors.add(context.resources.getColor(R.color.red, null))
            colors.add(context.resources.getColor(R.color.green, null))
            colors.add(context.resources.getColor(R.color.purple_200, null))
            colors.add(context.resources.getColor(R.color.teal_700, null))
            colors.add(context.resources.getColor(R.color.black, null))
            colors.add(context.resources.getColor(R.color.orange, null))
            colors.add(context.resources.getColor(R.color.yellow, null))
            return colors
        }
    }
}