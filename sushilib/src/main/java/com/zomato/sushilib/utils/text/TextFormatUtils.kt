package com.zomato.sushilib.utils.text

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import com.zomato.sushilib.R

class TextFormatUtils {
    companion object {
        fun zTextSizeToAndroidSp(zTextSize: Int): Float {
            return when (zTextSize) {
                100 -> 12f
                200 -> 14f
                300 -> 16f
                400 -> 18f
                500 -> 18f
                600 -> 28f
                700 -> 32f
                800 -> 36f
                900 -> 44f
                else -> 24f
            }
        }

        fun zFontWeightToTypeface(context: Context, zFontWeight: Int): Typeface? {
            val fontResIt = when (zFontWeight) {
                100 -> R.font.okra_thin
                200 -> R.font.okra_extralight
                300 -> R.font.okra_light
                400 -> R.font.okra_regular
                500 -> R.font.okra_medium
                600 -> R.font.okra_semibold
                else -> R.font.okra_regular
            }
            return ResourcesCompat.getFont(context, fontResIt)
        }
    }
}