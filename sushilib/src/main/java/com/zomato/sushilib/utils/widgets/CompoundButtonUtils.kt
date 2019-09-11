package com.zomato.sushilib.utils.widgets

import android.content.Context
import android.content.res.ColorStateList
import androidx.annotation.ColorInt
import androidx.annotation.RestrictTo
import androidx.core.content.ContextCompat

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal object CompoundButtonUtils {

    fun getBackgroundTintList(context: Context, @ColorInt color: Int): ColorStateList {
        return ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf()
            ),
            intArrayOf(
                color,
                ContextCompat.getColor(context, com.zomato.sushilib.R.color.sushi_grey_400)
            )
        )
    }
}