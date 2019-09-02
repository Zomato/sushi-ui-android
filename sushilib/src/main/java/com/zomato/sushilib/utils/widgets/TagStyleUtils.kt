package com.zomato.sushilib.utils.widgets

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.RestrictTo
import androidx.core.content.ContextCompat
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.TagSize
import com.zomato.sushilib.annotations.TagType
import com.zomato.sushilib.molecules.tags.SushiTag

/**
 * created by championswimmer on 24/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal object TagStyleUtils {
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
                textApprStyleRes = R.style.TextAppearance_Sushi_SemiBold
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
            }
        }
        if (tagType == TagType.CAPSULE || tagType == TagType.CAPSULE_OUTLINE) {
            // Capsule tags look better with a little more padding
            horizontalPadding = (horizontalPadding * 1.5).toInt()
        }
        if (mPadding != -1) {
            verticalPadding = mPadding
            horizontalPadding = mPadding
        }
        if (mPaddingLeft == -1) mPaddingLeft = horizontalPadding
        if (mPaddingRight == -1) mPaddingRight = horizontalPadding
        if (mPaddingTop == -1) mPaddingTop = verticalPadding
        if (mPaddingBottom == -1) mPaddingBottom = verticalPadding

        setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom)
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePx)
        setTextAppearance(textApprStyleRes)
        includeFontPadding = false
    }

    @JvmStatic
    private fun SushiTag.applyBg(
        @DrawableRes bgDrawable: Int, colorList: ColorStateList,
        outlined: Boolean = false, dashed: Boolean = false
    ) {
        background = ContextCompat.getDrawable(context, bgDrawable)
        background.mutate()
        backgroundTintList = colorList
        if (outlined) {
            (background as GradientDrawable).setColor(Color.TRANSPARENT)
            (background as GradientDrawable).setStroke(
                resources.getDimensionPixelSize(R.dimen.sushi_spacing_pico),
                colorList,
                10F.takeIf { dashed } ?: 0F,
                10F.takeIf { dashed } ?: 0F
            )
            setTextColor(colorList)
        } else {
            setTextColor(ContextCompat.getColor(context, R.color.sushi_white))
            (background as? GradientDrawable)?.setStroke(0, 0)
        }

    }

    @JvmStatic
    fun SushiTag.applyBackground() {
        when (tagType) {
            TagType.ROUNDED -> applyBg(
                R.drawable.sushi_tag_rounded_bg, ColorStateList.valueOf(tagColor)
            )
            TagType.CAPSULE -> applyBg(
                R.drawable.sushi_tag_capsule_bg, ColorStateList.valueOf(tagColor)
            )
            TagType.ROUNDED_OUTLINE -> applyBg(
                R.drawable.sushi_tag_rounded_bg, ColorStateList.valueOf(tagColor), true
            )
            TagType.CAPSULE_OUTLINE -> applyBg(
                R.drawable.sushi_tag_capsule_bg, ColorStateList.valueOf(tagColor), true
            )
            TagType.ROUNDED_DASHED -> applyBg(
                R.drawable.sushi_tag_capsule_bg, ColorStateList.valueOf(tagColor), true, true
            )
            TagType.CAPSULE_DASHED -> applyBg(
                R.drawable.sushi_tag_capsule_bg, ColorStateList.valueOf(tagColor), true, true
            )
        }
    }

    @JvmStatic
    @DimenRes
    fun getMinWidthResIdForRatingTagBySize(@TagSize tagSize: Int): Int {
        return when (tagSize) {
            TagSize.LARGE -> R.dimen.sushi_rating_tag_large_minwidth
            TagSize.MEDIUM -> R.dimen.sushi_rating_tag_medium_minwidth
            TagSize.SMALL -> R.dimen.sushi_rating_tag_small_minwidth
            TagSize.TINY -> R.dimen.sushi_rating_tag_tiny_minwidth
            else -> R.dimen.sushi_rating_tag_large_minwidth
        }
    }
}