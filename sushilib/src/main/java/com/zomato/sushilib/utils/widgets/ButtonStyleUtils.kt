package com.zomato.sushilib.utils.widgets

import android.animation.AnimatorInflater
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.RestrictTo
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.ButtonType
import com.zomato.sushilib.atoms.buttons.SushiButton
import com.zomato.sushilib.utils.widgets.TextViewUtils.setCompoundDrawableTintListCompat

/**
 * A collection of static functions that help
 * setup (and change) the style of SushiButton
 *
 * The reason this is an `object` with `@JvmStatic`
 * functions inside, is so that is easy to consume
 * in both Java and Kotlin. For Java consumers,
 * call `ButtonStyleUtils.applyXXX(buttonInstance)`
 * where applyXXX is one of the functions here
 *
 * created by championswimmer on 19/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal object ButtonStyleUtils {
    /**
     * Apply stroke width if button type is [ButtonType.OUTLINE]
     */
    @JvmStatic
    fun SushiButton.applyStrokeWidth() {
        strokeWidth = when {
            getButtonType() == ButtonType.TEXT -> 0
            strokeWidth != 0 -> strokeWidth
            getButtonType() == ButtonType.OUTLINE -> resources.getDimensionPixelSize(R.dimen.sushi_outline_button_stroke_width)
            else -> 0
        }
    }

    @JvmStatic
    fun SushiButton.applyIconAndTextColor(color: Int? = null) {
        val colorStateList = if (getButtonType() == ButtonType.SOLID) {
            getTextColorStateList(context, color ?: Color.WHITE)
        } else {
            getTextColorStateList(context, color ?: getButtonColor())
        }
        setTextColor(colorStateList)
        setCompoundDrawableTintListCompat(colorStateList)
    }

    @JvmStatic
    fun SushiButton.applyStrokeColor(@ColorInt strokeColorInt: Int) {
        strokeColor = getTextColorStateList(context, strokeColorInt)
    }

    /**
     * Apply ripple color to the button.
     * For buttonType = [ButtonType.SOLID] we use a white alpha ripple
     * otherwise we use a ripple based on the button's [SushiButton.getButtonColor]
     */
    @JvmStatic
    fun SushiButton.applyRippleColor() {
        val buttonType = getButtonType()
        if (buttonType == ButtonType.SOLID) {
            rippleColor = getButtonRippleStateList(
                if (getButtonColor() == Color.WHITE) ContextCompat.getColor(
                    context,
                    R.color.sushi_grey_500
                ) else Color.WHITE
            )
        } else if (buttonType == ButtonType.OUTLINE) {
            rippleColor = getButtonRippleStateList(getButtonColor())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (buttonType == ButtonType.TEXT) {
                stateListAnimator =
                    AnimatorInflater.loadStateListAnimator(context, R.animator.sushi_text_button_click_animator)
            } else {
                stateListAnimator = null
            }
        }
    }

    @JvmStatic
    fun SushiButton.applyBackgroundTintList() {
        backgroundTintList = if (getButtonType() == ButtonType.SOLID) {
            getButtonBackgroundTintList(context, getButtonColor())
        } else {
            ColorStateList.valueOf(Color.argb(0, 255, 255, 255)) // #00FFFFFF
        }
    }

    @JvmStatic
    private fun getTextColorStateList(context: Context, @ColorInt color: Int): ColorStateList {
//        <item android:color="?attr/colorAccent" android:state_enabled="true"/>
//        <item android:color="@color/mtrl_btn_text_color_disabled"/>
        return ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_enabled),
                intArrayOf()
            ),
            intArrayOf(
                color,
                ContextCompat.getColor(context, R.color.sushi_button_text_color_disabled)
            )
        )
    }

    @JvmStatic
    private fun getButtonBackgroundTintList(context: Context, @ColorInt color: Int): ColorStateList {
//        <item android:color="?attr/colorAccent" android:state_enabled="true"/>
//        <item android:color="@color/mtrl_btn_bg_color_disabled"/>
        return ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_enabled),
                intArrayOf()
            ),
            intArrayOf(
                color,
                ContextCompat.getColor(context, R.color.sushi_button_bg_color_disabled)
            )
        )
    }

    @JvmStatic
    private fun getButtonRippleStateList(@ColorInt color: Int): ColorStateList {
//        <item android:alpha="0.16" android:color="?attr/colorAccent" android:state_pressed="true"/>
//        <item android:alpha="0.12" android:color="?attr/colorAccent" android:state_focused="true" android:state_hovered="true"/>
//        <item android:alpha="0.12" android:color="?attr/colorAccent" android:state_focused="true"/>
//        <item android:alpha="0.04" android:color="?attr/colorAccent" android:state_hovered="true"/>
//        <item android:alpha="0.00" android:color="?attr/colorAccent"/>

        return ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_pressed),
                intArrayOf(android.R.attr.state_focused, android.R.attr.state_hovered),
                intArrayOf(android.R.attr.state_focused),
                intArrayOf(android.R.attr.state_hovered),
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
