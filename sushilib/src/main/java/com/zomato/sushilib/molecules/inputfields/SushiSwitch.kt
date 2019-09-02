package com.zomato.sushilib.molecules.inputfields

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.theme.ResourceThemeResolver

/**
 * created by championswimmer on 01/05/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
class SushiSwitch @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = R.attr.switchStyle, @StyleRes defStyleRes: Int = 0
) : SwitchCompat(ctx, attrs, defStyleAttr) {

    @ColorInt
    private var color: Int = ResourceThemeResolver.getThemedColorFromAttr(context, android.R.attr.colorControlActivated)

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SushiSwitch,
            defStyleAttr, defStyleRes
        ).let {
            color = it.getColor(R.styleable.SushiSwitch_controlColor, color)
            applyTints()
            it.recycle()
        }
    }

    fun setControlColor(@ColorInt color: Int) {
        if (this.color == color) return
        this.color = color
        applyTints()
    }

    private fun applyTints() {
        thumbTintList = createThumbTintList()
        trackTintList = createTrackTintList()
    }

    private fun createThumbTintList(): ColorStateList {
        return ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf()
            ),
            intArrayOf(
                color,
                ContextCompat.getColor(context, R.color.sushi_grey_400)
            )
        )
    }

    private fun createTrackTintList(): ColorStateList {
        return ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf()
            ),
            intArrayOf(
                // 30% alpha
                ColorUtils.setAlphaComponent(color, 77),
                ColorUtils.setAlphaComponent(ContextCompat.getColor(context, R.color.sushi_grey_400), 77)
            )
        )
    }

}