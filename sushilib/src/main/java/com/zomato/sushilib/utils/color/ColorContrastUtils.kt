package com.zomato.sushilib.utils.color

import android.graphics.Color

class ColorContrastUtils {
    companion object {
        /**
         * Tells if the color is dark or not
         * For eg. if dark color, use a light font
         * on it
         * @param color Android color integer
         */
        @JvmStatic
        fun isDarkColor (color: Int): Boolean {
            val r = Color.red(color)
            val g = Color.green(color)
            val b = Color.blue(color)
            val yiq = ((r*299)+(g*587)+(b*114))/1000
            return (yiq < 128)
        }
    }
}