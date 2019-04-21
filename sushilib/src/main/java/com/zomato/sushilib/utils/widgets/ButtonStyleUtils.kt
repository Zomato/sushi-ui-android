package com.zomato.sushilib.utils.widgets

import android.R.attr.state_enabled
import android.R.attr.state_focused
import android.R.attr.state_hovered
import android.R.attr.state_pressed
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.v4.graphics.ColorUtils
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.ButtonType
import com.zomato.sushilib.atoms.buttons.SushiButton

/**
 * created by championswimmer on 19/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
object ButtonStyleUtils {
    @JvmStatic
    fun SushiButton.applyStrokeWidth() {
        strokeWidth = if (getButtonType() == ButtonType.OUTLINE) {
            resources.getDimensionPixelSize(R.dimen.sushi_outline_button_stroke_width)
        } else {
            0
        }
    }

    @JvmStatic
    fun SushiButton.applyIconPadding() {
        iconPadding = if (getButtonType() == ButtonType.TEXT) {
            resources.getDimensionPixelSize(R.dimen.sushi_text_button_icon_padding)
        } else {
            resources.getDimensionPixelSize(R.dimen.sushi_button_icon_padding)
        }
    }

    @JvmStatic
    fun SushiButton.applyIconAndTextColor() {
        val colorStateList = if (getButtonType() == ButtonType.SOLID) {
            getTextColorStateList(Color.WHITE)
        } else {
            getTextColorStateList(getButtonColor())
        }
        setTextColor(colorStateList)
        iconTint = colorStateList

        // TODO: Handle stroke color independently
        strokeColor = colorStateList
    }

    @JvmStatic
    fun SushiButton.applyRippleColor() {
        rippleColor = if (getButtonType() == ButtonType.SOLID) {
            getButtonRippleStateList(Color.WHITE)
        } else {
            getButtonRippleStateList(getButtonColor())
        }
    }

    @JvmStatic
    fun SushiButton.applyBackgroundTintList() {
        backgroundTintList = if (getButtonType() == ButtonType.SOLID) {
            getButtonBackgroundTintList(getButtonColor())
        } else {
            ColorStateList.valueOf(Color.argb(0, 255, 255, 255)) // #00FFFFFF
        }
    }

    @JvmStatic
    fun getTextColorStateList(@ColorInt color: Int): ColorStateList {
//        <item android:color="?attr/colorAccent" android:state_enabled="true"/>
//        <item android:color="@color/mtrl_btn_text_color_disabled"/>
        return ColorStateList(
            arrayOf(
                intArrayOf(state_enabled),
                intArrayOf()
            ),
            intArrayOf(
                color,
                Color.argb(97, 0, 0, 0) // #61000000
            )
        )
    }

    @JvmStatic
    fun getButtonBackgroundTintList(@ColorInt color: Int): ColorStateList {
//        <item android:color="?attr/colorAccent" android:state_enabled="true"/>
//        <item android:color="@color/mtrl_btn_bg_color_disabled"/>
        return ColorStateList(
            arrayOf(
                intArrayOf(state_enabled),
                intArrayOf()
            ),
            intArrayOf(
                color,
                Color.argb(31, 0, 0, 0) // #1F000000
            )
        )

    }

    @JvmStatic
    fun getButtonRippleStateList(@ColorInt color: Int): ColorStateList {
//        <item android:alpha="0.16" android:color="?attr/colorAccent" android:state_pressed="true"/>
//        <item android:alpha="0.12" android:color="?attr/colorAccent" android:state_focused="true" android:state_hovered="true"/>
//        <item android:alpha="0.12" android:color="?attr/colorAccent" android:state_focused="true"/>
//        <item android:alpha="0.04" android:color="?attr/colorAccent" android:state_hovered="true"/>
//        <item android:alpha="0.00" android:color="?attr/colorAccent"/>

        return ColorStateList(
            arrayOf(
                intArrayOf(state_pressed),
                intArrayOf(state_focused, state_hovered),
                intArrayOf(state_focused),
                intArrayOf(state_hovered),
                intArrayOf()
            ),
            intArrayOf(
                ColorUtils.setAlphaComponent(color, ((0.16 * 255).toInt())),
                ColorUtils.setAlphaComponent(color, ((0.12 * 255).toInt())),
                ColorUtils.setAlphaComponent(color, ((0.12 * 255).toInt())),
                ColorUtils.setAlphaComponent(color, ((0.04 * 255).toInt())),
                ColorUtils.setAlphaComponent(color, ((0.00 * 255).toInt()))
            )
        )
    }
}