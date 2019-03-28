package com.zomato.sushilib.utils.text

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.res.ResourcesCompat
import com.zomato.sushilib.R

class TextFormatUtils {
    companion object {
        fun zTextSizeToAndroidPx(context: Context?, zTextSize: Int): Float {
            return when (zTextSize) {
                100 -> context?.resources?.getDimension(R.dimen.sushi_textsize_100) ?: 10f
                200 -> context?.resources?.getDimension(R.dimen.sushi_textsize_200) ?: 12f
                300 -> context?.resources?.getDimension(R.dimen.sushi_textsize_300) ?: 13f
                400 -> context?.resources?.getDimension(R.dimen.sushi_textsize_400) ?: 15f
                500 -> context?.resources?.getDimension(R.dimen.sushi_textsize_500) ?: 17f
                600 -> context?.resources?.getDimension(R.dimen.sushi_textsize_600) ?: 19f
                700 -> context?.resources?.getDimension(R.dimen.sushi_textsize_700) ?: 21f
                800 -> context?.resources?.getDimension(R.dimen.sushi_textsize_800) ?: 24f
                900 -> context?.resources?.getDimension(R.dimen.sushi_textsize_900) ?: 27f
                else -> context?.resources?.getDimension(R.dimen.sushi_textsize_400) ?: 15f
            }.toFloat()
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