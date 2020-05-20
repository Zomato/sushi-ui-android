package com.zomato.sushilib.utils.theme

import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat


/**
 * created by championswimmer on 09/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
object ResourceThemeResolver {

    /**
     * Creates a theme-wrapped context, given a context and a default style resId
     *
     * @param context Context wrapped with provided [defStyleRes]
     * or the same [context] is returned if no defStyleRes is provided
     * @param defStyleRes A style resource id
     */
    @JvmStatic
    fun getThemeWrappedContext(context: Context?, @StyleRes defStyleRes: Int = 0): Context? {
        return (if (defStyleRes == 0) {
            context
        } else {
            ContextThemeWrapper(context, defStyleRes)
        })
    }

    @JvmStatic
    fun getThemedFontFromAttr(context: Context, @AttrRes attrId: Int): Typeface? {
        val outValue = TypedValue()
        context.theme.resolveAttribute(
            attrId,
            outValue, true
        )
        return try {
            ResourcesCompat.getFont(context, outValue.resourceId)
        } catch (e: Resources.NotFoundException) {
            Typeface.DEFAULT
        }
    }

    @JvmStatic
    @ColorInt
    fun getThemedColorFromAttr(context: Context, @AttrRes attrId: Int): Int {
        val outValue = TypedValue()
        context.theme.resolveAttribute(
            attrId,
            outValue, true
        )
        return ContextCompat.getColor(context, outValue.resourceId)
    }

    @JvmStatic
    fun getThemedDimenPixelFromAttr(context: Context, @AttrRes attrId: Int): Int {
        val outValue = TypedValue()
        context.theme.resolveAttribute(
            attrId,
            outValue, true
        )
        return context.resources.getDimensionPixelSize(outValue.resourceId)
    }

    @JvmStatic
    fun getThemedDrawableFromAttr(context: Context, @AttrRes attrId: Int): Drawable? {
        val outValue = TypedValue()
        context.theme.resolveAttribute(
            attrId,
            outValue, true
        )
        return ContextCompat.getDrawable(context, outValue.resourceId)
    }
}