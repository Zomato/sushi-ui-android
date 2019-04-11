package com.zomato.sushilib.utils.theme

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.TypedValue


/**
 * created by championswimmer on 09/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
object ResourceThemeResolver {

    @JvmStatic
    fun getThemedColorFromAttr(context: Context, attrId: Int): Int {
        val outValue = TypedValue()
        context.theme.resolveAttribute(
            attrId,
            outValue, true
        )
        return ContextCompat.getColor(context, outValue.resourceId)
    }

    @JvmStatic
    fun getThemedDimenPixelFromAttr(context: Context, attrId: Int): Int {
        val outValue = TypedValue()
        context.theme.resolveAttribute(
            attrId,
            outValue, true
        )
        return context.resources.getDimensionPixelSize(outValue.resourceId)
    }

    @JvmStatic
    fun getThemedDrawableFromAttr(context: Context, attrId: Int): Drawable? {
        val outValue = TypedValue()
        context.theme.resolveAttribute(
            attrId,
            outValue, true
        )
        return ContextCompat.getDrawable(context, outValue.resourceId)
    }
}