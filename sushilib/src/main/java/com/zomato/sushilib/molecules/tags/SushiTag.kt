package com.zomato.sushilib.molecules.tags

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.annotation.StyleRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.TagSize
import com.zomato.sushilib.annotations.TagType
import com.zomato.sushilib.atoms.textviews.SushiTextView
import com.zomato.sushilib.utils.theme.ResourceThemeResolver
import com.zomato.sushilib.utils.widgets.DrawableSetters
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
) : SushiTextView(ctx, attrs, defStyleAttr, defStyleRes), DrawableSetters {

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


            initialized = true
            reapplyTagStyles()

            applyDrawables(
                attrs, defStyleAttr,
                ContextCompat.getColor(context, R.color.sushi_white) ?: Color.WHITE,
                (textSize * 0.9).toInt() // Looks best, at slightly (10%) less than text height
            )

            it.getColor(R.styleable.SushiTag_android_textColor, -1).let { col ->
                if (col != -1) setTextColor(col)
            }

            it.recycle()
        }
    }

    override fun setTextColor(colors: ColorStateList?) {
        super.setTextColor(colors)
        compoundDrawableTintList = colors
    }

    private fun reapplyTagStyles() {
        if (!initialized) return

        TagStyleUtils.apply {
            applySize()
            applyBackground()
        }
    }

}