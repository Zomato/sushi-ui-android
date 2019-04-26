package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.StyleRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.TagSize
import com.zomato.sushilib.annotations.TagType
import com.zomato.sushilib.utils.theme.ResourceThemeResolver
import com.zomato.sushilib.utils.widgets.TagStyleUtils
import com.zomato.sushilib.utils.widgets.TextViewUtils.applyDrawables

/**
 * created by championswimmer on 24/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
open class SushiTag @JvmOverloads constructor(
    ctx: Context?, attrs: AttributeSet?,
    @AttrRes defStyleAttr: Int = R.attr.tagStyle,
    @StyleRes defStyleRes: Int = R.style.Widget_Sushi_Tag
) : SushiTextView(ctx, attrs, defStyleAttr, defStyleRes) {

    private var initialized = false

    @get:TagType
    @setparam:TagType
    var tagType = TagType.ROUNDED
        set(value) {
            field = value
            reapplyTagStyles()
        }

    @get:TagSize
    @setparam:TagSize
    var tagSize = TagSize.LARGE
        set(value) {
            field = value
            reapplyTagStyles()
        }

    @get:ColorInt
    @setparam:ColorInt
    var tagColor = ResourceThemeResolver.getThemedColorFromAttr(context, android.R.attr.colorAccent)
        set(value) {
            field = value
            reapplyTagStyles()
        }

    init {

        context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.SushiTag,
            defStyleAttr,
            defStyleRes
        )?.let {

            tagType = it.getInt(R.styleable.SushiTag_tagType, TagType.ROUNDED)
            tagSize = it.getInt(R.styleable.SushiTag_tagSize, TagSize.LARGE)
            tagColor = it.getColor(R.styleable.SushiTag_tagColor, tagColor)

            applyDrawables(
                it,
                R.styleable.SushiTag_drawableLeft,
                R.styleable.SushiTag_drawableRight,
                R.styleable.SushiTag_drawableStart,
                R.styleable.SushiTag_drawableEnd,
                R.styleable.SushiTag_drawablePadding,
                ContextCompat.getColor(context, R.color.sushi_white) ?: Color.WHITE,
                (textSize * 0.8).toInt()
            )

            initialized = true
            reapplyTagStyles()

            it.getColor(R.styleable.SushiTag_android_textColor, -1).let { col ->
                if (col != -1) setTextColor(col)
            }

            it.recycle()
        }
    }

    private fun reapplyTagStyles() {
        if (!initialized) return

        TagStyleUtils.apply {
            applySize()
            applyBackground()
        }
    }

}