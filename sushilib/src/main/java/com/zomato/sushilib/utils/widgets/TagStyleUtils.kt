package com.zomato.sushilib.utils.widgets

import android.content.res.ColorStateList
import android.support.annotation.StyleRes
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.util.TypedValue
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.TagSize
import com.zomato.sushilib.annotations.TagType
import com.zomato.sushilib.atoms.textviews.SushiTag

/**
 * created by championswimmer on 24/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
object TagStyleUtils {
    @JvmStatic
    fun SushiTag.applySize() {
        var verticalPadding = 0
        var horizontalPadding = 0
        var textSizePx = 0f
        var textApprStyleRes = R.style.TextAppearance_Sushi_Medium
        val r = context.resources
        when (tagSize) {
            TagSize.LARGE -> {
                verticalPadding = r.getDimensionPixelSize(R.dimen.sushi_tag_large_vertical_padding)
                horizontalPadding = r.getDimensionPixelSize(R.dimen.sushi_tag_large_horizontal_padding)
                textSizePx = r.getDimension(R.dimen.sushi_tag_large_textsize)
                textApprStyleRes = R.style.TextAppearance_Sushi_SemiBold
            }
            TagSize.MEDIUM -> {
                verticalPadding = r.getDimensionPixelSize(R.dimen.sushi_tag_medium_vertical_padding)
                horizontalPadding = r.getDimensionPixelSize(R.dimen.sushi_tag_medium_horizontal_padding)
                textSizePx = r.getDimension(R.dimen.sushi_tag_medium_textsize)
            }
            TagSize.SMALL -> {
                verticalPadding = r.getDimensionPixelSize(R.dimen.sushi_tag_small_vertical_padding)
                horizontalPadding = r.getDimensionPixelSize(R.dimen.sushi_tag_small_horizontal_padding)
                textSizePx = r.getDimension(R.dimen.sushi_tag_small_textsize)
            }
            TagSize.TINY -> {
                verticalPadding = r.getDimensionPixelSize(R.dimen.sushi_tag_tiny_vertical_padding)
                horizontalPadding = r.getDimensionPixelSize(R.dimen.sushi_tag_tiny_horizontal_padding)
                textSizePx = r.getDimension(R.dimen.sushi_tag_tiny_textsize)
                textApprStyleRes = R.style.TextAppearance_Sushi_Medium
            }
        }
        if (tagType == TagType.CAPSULE) {
            // Capsule tags look better with a little more padding
            horizontalPadding = (horizontalPadding * 1.5).toInt()
        }
        // Tiny tags have no fontPadding
        setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePx)
        setTextAppearance(textApprStyleRes)
        includeFontPadding = false
    }

    @JvmStatic
    fun SushiTag.applyBackground() {
        background = when(tagType) {
            TagType.ROUNDED -> ContextCompat.getDrawable(context, R.drawable.sushi_tag_rounded_bg)
            TagType.CAPSULE -> ContextCompat.getDrawable(context, R.drawable.sushi_tag_capsule_bg)
            else -> ContextCompat.getDrawable(context, R.drawable.sushi_tag_rounded_bg)
        }
        backgroundTintList = ColorStateList.valueOf(tagColor)
    }
}