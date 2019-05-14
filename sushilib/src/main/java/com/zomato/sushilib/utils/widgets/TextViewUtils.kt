package com.zomato.sushilib.utils.widgets

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.annotation.RestrictTo
import android.support.annotation.StyleableRes
import android.util.AttributeSet
import android.widget.TextView
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.drawables.SushiIconDrawable

/**
 * created by championswimmer on 26/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal object TextViewUtils {
    @StyleableRes
    private val styleableDrLeft: Int = R.styleable.SushiTextView_drawableLeft
    @StyleableRes
    private val styleableDrRight: Int = R.styleable.SushiTextView_drawableRight
    @StyleableRes
    private val styleableDrStart: Int = R.styleable.SushiTextView_drawableStart
    @StyleableRes
    private val styleableDrEnd: Int = R.styleable.SushiTextView_drawableEnd
    @StyleableRes
    private val styleableDrPadding: Int = R.styleable.SushiTextView_drawablePadding
    @StyleableRes
    private val styleableDrTint: Int = R.styleable.SushiTextView_drawableTint

    @JvmStatic
    fun TextView.applyDrawables(
        attrs: AttributeSet?,
        @AttrRes defStyleAttr: Int = 0,
        @ColorInt drColor: Int,
        iconSize: Int,
        iconFontScale: Float = 1f
    ) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.SushiTextView, defStyleAttr, 0)


        /**
         * Takes a fonticon character, builds a Drawable
         * Using [drColor] and [iconSize] in function arguments
         *
         * Extracted as local function because reused below
         *
         * @param iconChar Wasabi font icon character
         */
        fun stringToIconDrawable(iconChar: String): Drawable =
            SushiIconDrawable.Builder(context)
                .setIconChar(iconChar)
                .setColor(drColor)
                .setIconSize((iconSize * iconFontScale).toInt())
                .build()

        /**
         * Takes a styleable attribute, and tries to turn it into a drawable
         *
         * 1. If it refers to a @drawable/xx or @android:drawable/xxx
         * 2. If it is a string, tries to turn it into a [SushiIconDrawable]
         * 3. Or else, returns null
         *
         * @param drStyleableId R.styleable.xxxx type id of the drawable field
         */
        fun createDrawableFromAttr(@StyleableRes drStyleableId: Int): Drawable? =
            try {
                ta.getDrawable(drStyleableId)?.apply {
                    setBounds(0, 0, iconSize, iconSize)
                }
            } catch (exception: Resources.NotFoundException) {
                ta.getString(drStyleableId)?.let {
                    stringToIconDrawable(it)
                }
            }

        val drawableLeft = createDrawableFromAttr(styleableDrLeft)
        val drawableStart = createDrawableFromAttr(styleableDrStart)
        val drawableRight = createDrawableFromAttr(styleableDrRight)
        val drawableEnd = createDrawableFromAttr(styleableDrEnd)

        setCompoundDrawables(
            drawableLeft,
            null,
            drawableRight,
            null
        )
        if (drawableStart != null || drawableEnd != null) {
            setCompoundDrawablesRelative(
                drawableStart,
                null,
                drawableEnd,
                null
            )
        }
        // =========== SETTING PADDINGS =====================


        compoundDrawablePadding = ta.getDimensionPixelSize(
            styleableDrPadding,
            0
        )

        // =========== SETTING TINT =====================
        val iconTintList = ta.getColorStateList(
            R.styleable.SushiTextView_drawableTint
        ) ?: textColors

        setCompoundDrawableTintListCompat(iconTintList)

        ta.recycle()
    }

    @JvmStatic
    fun TextView.setCompoundDrawableTintListCompat(tintList: ColorStateList?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.compoundDrawableTintList = tintList
        } else {
            compoundDrawables.forEach { d ->
                d?.setTintList(tintList)
            }
            compoundDrawablesRelative.forEach { d ->
                d?.setTintList(tintList)
            }
        }
    }
}