package com.zomato.sushilib.utils.text

import androidx.annotation.RestrictTo
import androidx.annotation.StyleRes
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.FontWeight
import com.zomato.sushilib.annotations.FontWeight.*

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal object TextFormatUtils {
    @JvmStatic
    @StyleRes
    fun textFontWeightToTextAppearance(@FontWeight textFontWeight: Int): Int {
        return when (textFontWeight) {
            THIN -> R.style.TextAppearance_Sushi_Thin
            EXTRALIGHT -> R.style.TextAppearance_Sushi_ExtraLight
            LIGHT -> R.style.TextAppearance_Sushi_Light
            REGULAR -> R.style.TextAppearance_Sushi_Regular
            MEDIUM -> R.style.TextAppearance_Sushi_Medium
            SEMIBOLD -> R.style.TextAppearance_Sushi_SemiBold
            BOLD -> R.style.TextAppearance_Sushi_Bold
            EXTRABOLD -> R.style.TextAppearance_Sushi_ExtraBold
            else -> R.style.TextAppearance_Sushi_Regular
        }
    }
}