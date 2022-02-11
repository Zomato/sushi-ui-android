package com.zomato.sushilib.utils.color

import androidx.annotation.RestrictTo

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal object ColorContrastUtils {
    /**
     * Tells if the color is dark or not
     * For eg. if dark color, use a light font
     * on it
     * @param color Android color integer
     */
    @JvmStatic
    fun isDarkColor(color: Int): Boolean {
        val r = color shr 16 and 0xFF // Color.red(color)
        val g = color shr 8 and 0xFF // Color.green(color)
        val b = color and 0xFF // Color.blue(color)
        val yiq = ((r * 299) + (g * 587) + (b * 114)) / 1000
        return (yiq < 128)
    }
}