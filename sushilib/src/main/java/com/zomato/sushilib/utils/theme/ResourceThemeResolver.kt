package com.zomato.sushilib.utils.theme

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import com.zomato.sushilib.R


/**
 * created by championswimmer on 09/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
object ResourceThemeResolver {

    @SuppressLint("NewApi")
    @JvmStatic
    fun getThemedColorFromAttr(context: Context, attrId: Int): Int {
        val outValue = TypedValue()
        context.theme.resolveAttribute(
            attrId,
            outValue, true
        )
       return context.getColor(outValue.resourceId)
    }
}