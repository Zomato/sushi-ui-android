package com.zomato.sushilib.utils.widgets

import android.graphics.drawable.Drawable
import android.widget.TextView
import com.zomato.sushilib.atoms.drawables.SushiIconDrawable

/**
 * created by championswimmer on 2019-05-16
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
internal interface DrawableSetters {

    private fun TextView.iconSize(): Int = (textSize * 0.9).toInt()
    private fun TextView.createDrawable(iconFontChar: String) = SushiIconDrawable.Builder(context)
        .setIconChar(iconFontChar)
        .setTintList(textColors)
        .setIconSize((textSize * 0.9).toInt())
        .build()

    /**
     * Set the left drawable
     * NOTE: this is not RTL layout direction compliant.
     * It is recommended to use [setDrawableStart] instead
     *
     * @param drawable the drawable to set, null
     */
    fun TextView.setDrawableLeft(drawable: Drawable?) {
        val drawables = compoundDrawables
        drawable?.apply {
            setBounds(0, 0, iconSize(), iconSize())
        }
        setCompoundDrawables(drawable, drawables[1], drawables[2], drawables[3])
    }

    /**
     * Set the right drawable
     * NOTE: this is not RTL layout direction compliant.
     * It is recommended to use [setDrawableEnd] instead
     *
     * @param drawable the drawable to set, null
     */
    fun TextView.setDrawableRight(drawable: Drawable?) {
        val drawables = compoundDrawables
        drawable?.apply {
            setBounds(0, 0, iconSize(), iconSize())
        }
        setCompoundDrawables(drawables[0], drawables[1], drawable, drawables[3])
    }

    /**
     * set the left drawable using an iconfont character
     * WARNING: Setting invalid icon font character can lead to
     * unpredictable drawables.
     *
     * NOTE: this is not RTL layout direction compliant.
     * It is recommended to use [setDrawableStart] instead
     *
     * @param iconFontChar the icon font character
     */
    fun TextView.setDrawableLeft(iconFontChar: String?) {
        val drawables = compoundDrawables
        val drawable = iconFontChar?.let { createDrawable(it) }

        setCompoundDrawables(drawable, drawables[1], drawables[2], drawables[3])
    }

    /**
     * set the right drawable using an iconfont character
     * WARNING: Setting invalid icon font character can lead to
     * unpredictable drawables.
     *
     * NOTE: this is not RTL layout direction compliant.
     * It is recommended to use [setDrawableStart] instead
     *
     * @param iconFontChar the icon font character
     */
    fun TextView.setDrawableRight(iconFontChar: String?) {
        val drawables = compoundDrawables
        val drawable = iconFontChar?.let { createDrawable(it) }

        setCompoundDrawables(drawables[0], drawables[1], drawable, drawables[3])
    }


    /**
     * Set the start drawable (left for LTR and right for RTL layouts)
     * @param drawable the drawable to set, null
     */
    fun TextView.setDrawableStart(drawable: Drawable?) {
        val drawables = compoundDrawablesRelative
        drawable?.apply {
            setBounds(0, 0, iconSize(), iconSize())
        }
        setCompoundDrawablesRelative(drawable, drawables[1], drawables[2], drawables[3])
    }

    /**
     * Set the end drawable (right for LTR and left for RTL layouts)
     * @param drawable the drawable to set, null
     */
    fun TextView.setDrawableEnd(drawable: Drawable?) {
        val drawables = compoundDrawablesRelative
        drawable?.apply {
            setBounds(0, 0, iconSize(), iconSize())
        }
        setCompoundDrawablesRelative(drawables[0], drawables[1], drawable, drawables[3])
    }

    /**
     * Set the start drawable (left for LTR and right for RTL layouts)
     * using an iconfont character
     * WARNING: Setting invalid icon font character can lead to
     * unpredictable drawables.
     *
     * @param drawable the drawable to set, null
     */

    fun TextView.setDrawableStart(iconFontChar: String?) {
        val drawables = compoundDrawablesRelative
        val drawable = iconFontChar?.let { createDrawable(it) }

        setCompoundDrawablesRelative(drawable, drawables[1], drawables[2], drawables[3])
    }

    /**
     * Set the end drawable (right for LTR and left for RTL layouts)
     * using an iconfont character
     * WARNING: Setting invalid icon font character can lead to
     * unpredictable drawables.
     *
     * @param drawable the drawable to set, null
     */
    fun TextView.setDrawableEnd(iconFontChar: String?) {
        val drawables = compoundDrawablesRelative
        val drawable = iconFontChar?.let { createDrawable(it) }

        setCompoundDrawablesRelative(drawables[0], drawables[1], drawable, drawables[3])
    }
}