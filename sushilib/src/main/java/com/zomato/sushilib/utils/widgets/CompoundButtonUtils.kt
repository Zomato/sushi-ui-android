package com.zomato.sushilib.utils.widgets

import android.R
import android.content.Context
import android.content.res.ColorStateList
import android.support.annotation.ColorInt
import android.support.annotation.RestrictTo
import android.support.v4.content.ContextCompat

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal object CompoundButtonUtils {

    fun getBackgroundTintList(context: Context, @ColorInt color: Int): ColorStateList {
        return ColorStateList(
            arrayOf(
                intArrayOf(R.attr.state_checked),
                intArrayOf()
            ),
            intArrayOf(
                color,
                ContextCompat.getColor(context, com.zomato.sushilib.R.color.sushi_grey_400)
            )
        )
    }
}