package com.zomato.sushilib.utils.text

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.support.annotation.StyleRes
import android.support.v4.content.res.ResourcesCompat
import com.zomato.sushilib.R

object TextFormatUtils {
//    @JvmStatic
//    fun sushiFontWeightToTypeface(context: Context, sushiFontWeight: Int): Typeface? {
//        val fontResId = when (sushiFontWeight) {
//            100 -> R.font.okra_thin
//            200 -> R.font.okra_extralight
//            300 -> R.font.okra_light
//            400 -> R.font.okra_regular
//            500 -> R.font.okra_medium
//            600 -> R.font.okra_semibold
//            else -> R.font.okra_regular
//        }
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            context.resources.getFont(fontResId)
//        } else {
//            ResourcesCompat.getFont(context, fontResId)
//        }
//    }
    @JvmStatic @StyleRes
    fun sushiFontWeightToTextAppearance(sushiFontWeight: Int): Int {
        return when (sushiFontWeight) {
            100 -> R.style.TextAppearance_Sushi_Thin
            200 -> R.style.TextAppearance_Sushi_ExtraLight
            300 -> R.style.TextAppearance_Sushi_Light
            400 -> R.style.TextAppearance_Sushi_Regular
            500 -> R.style.TextAppearance_Sushi_Medium
            600 -> R.style.TextAppearance_Sushi_SemiBold
            else -> R.style.TextAppearance_Sushi_Regular
        }
    }
}