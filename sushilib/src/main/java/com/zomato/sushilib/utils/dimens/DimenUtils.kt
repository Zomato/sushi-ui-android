package com.zomato.sushilib.utils.dimens

import android.content.Context

/**
 * created by championswimmer on 2019-07-15
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
object DimenUtils {

    @JvmStatic
    fun dp2px (context: Context, dp: Float): Float {
        val scale= context.resources.displayMetrics.density;
        return (dp * scale + 0.5f)
    }

    @JvmStatic
    fun px2dp(context: Context, px: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }
}