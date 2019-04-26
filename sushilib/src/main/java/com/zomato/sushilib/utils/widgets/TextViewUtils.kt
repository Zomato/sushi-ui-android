package com.zomato.sushilib.utils.widgets

import android.content.res.ColorStateList
import android.content.res.Resources
import android.content.res.TypedArray
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.StyleableRes
import android.widget.TextView
import com.zomato.sushilib.atoms.drawables.SushiIconDrawable

/**
 * created by championswimmer on 26/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
object TextViewUtils {

    @JvmStatic
    fun TextView.applyDrawables(
        ta: TypedArray,
        @StyleableRes styleableDrLeft: Int,
        @StyleableRes styleableDrRight: Int,
        @StyleableRes styleableDrStart: Int,
        @StyleableRes styleableDrEnd: Int,
        @StyleableRes styleableDrPadding: Int,
        @ColorInt drColor: Int,
        iconSize: Int,
        iconTintList: ColorStateList = textColors
    ) {

        val drawableLeft =
            try {
                ta.getDrawable(styleableDrLeft)?.apply {
                    setBounds(0, 0, iconSize, iconSize)
                }
            } catch (exception: Resources.NotFoundException) {

                ta.getString(styleableDrLeft)?.let {
                    SushiIconDrawable.Builder(context)
                        .setIconChar(it)
                        .setColor(drColor)
                        .setIconSize(iconSize)
                        .build()
                }
            }
        val drawableStart =
            try {
                ta.getDrawable(styleableDrStart)?.apply {
                    setBounds(0, 0, iconSize, iconSize)
                }
            } catch (exception: Resources.NotFoundException) {

                ta.getString(styleableDrStart)?.let {
                    SushiIconDrawable.Builder(context)
                        .setIconChar(it)
                        .setColor(drColor)
                        .setIconSize(iconSize)
                        .build()
                }
            }
        val drawableRight =
            try {
                ta.getDrawable(styleableDrRight)?.apply {
                    setBounds(0, 0, iconSize, iconSize)
                }
            } catch (exception: Resources.NotFoundException) {

                ta.getString(styleableDrRight)?.let {
                    SushiIconDrawable.Builder(context)
                        .setIconChar(it)
                        .setColor(drColor)
                        .setIconSize(iconSize)
                        .build()
                }
            }
        val drawableEnd =
            try {
                ta.getDrawable(styleableDrEnd)?.apply {
                    setBounds(0, 0, iconSize, iconSize)
                }
            } catch (exception: Resources.NotFoundException) {

                ta.getString(styleableDrEnd)?.let {
                    SushiIconDrawable.Builder(context)
                        .setIconChar(it)
                        .setColor(drColor)
                        .setIconSize(iconSize)
                        .build()
                }
            }
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

        compoundDrawablePadding = ta.getDimensionPixelSize(
            styleableDrPadding,
            0
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            compoundDrawableTintList = iconTintList
        } else {
            compoundDrawables.forEach { d ->
                d.setTintList(iconTintList)
            }
        }

    }
}