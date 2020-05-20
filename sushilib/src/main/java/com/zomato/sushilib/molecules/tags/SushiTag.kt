package com.zomato.sushilib.molecules.tags

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.RestrictTo
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
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

    // Using hungarian notation to avoid clash with existing getter/setter of same
    @RestrictTo(RestrictTo.Scope.LIBRARY) internal var mPadding = -1
    @RestrictTo(RestrictTo.Scope.LIBRARY) internal var mPaddingLeft = -1
    @RestrictTo(RestrictTo.Scope.LIBRARY) internal var mPaddingRight = -1
    @RestrictTo(RestrictTo.Scope.LIBRARY) internal var mPaddingTop = -1
    @RestrictTo(RestrictTo.Scope.LIBRARY) internal var mPaddingBottom = -1

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

    @get:ColorInt
    @setparam:ColorInt
    var borderColor = ResourceThemeResolver.getThemedColorFromAttr(context, android.R.attr.colorAccent)
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
            borderColor = it.getColor(R.styleable.SushiTag_borderColor, tagColor)

            mPadding = it.getDimensionPixelSize(R.styleable.SushiTag_android_padding, -1)
            mPaddingLeft = it.getDimensionPixelSize(R.styleable.SushiTag_android_paddingLeft, -1)
            mPaddingRight = it.getDimensionPixelSize(R.styleable.SushiTag_android_paddingRight, -1)
            mPaddingTop = it.getDimensionPixelSize(R.styleable.SushiTag_android_paddingTop, -1)
            mPaddingBottom = it.getDimensionPixelSize(R.styleable.SushiTag_android_paddingBottom, -1)


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