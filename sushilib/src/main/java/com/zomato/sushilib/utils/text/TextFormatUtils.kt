package com.zomato.sushilib.utils.text

import android.support.annotation.RestrictTo
import android.support.annotation.StyleRes
import com.zomato.sushilib.R

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal object TextFormatUtils {
    @JvmStatic
    @StyleRes
    fun textFontWeightToTextAppearance(sushiFontWeight: Int): Int {
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