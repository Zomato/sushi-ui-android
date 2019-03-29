package com.zomato.sushilib.utils.text

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.res.ResourcesCompat
import com.zomato.sushilib.R

class TextFormatUtils {
    companion object {

        fun sushiFontWeightToTypeface(context: Context, sushiFontWeight: Int): Typeface? {
            val fontResIt = when (sushiFontWeight) {
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