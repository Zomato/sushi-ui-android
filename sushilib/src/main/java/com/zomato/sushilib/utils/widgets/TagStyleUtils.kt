package com.zomato.sushilib.utils.widgets

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import androidx.annotation.DimenRes
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
        var paddingLeft = 0
        var paddingRight = 0
        var paddingTop = 0
        var paddingBottom = 0
        var textSizePx = 0f
        var textApprStyleRes = R.style.TextAppearance_Sushi_Medium
        val r = context.resources
        when (tagSize) {
            TagSize.LARGE -> {
                paddingTop = r.getDimensionPixelSize(R.dimen.sushi_tag_large_vertical_padding)
                paddingLeft = r.getDimensionPixelSize(R.dimen.sushi_tag_large_horizontal_padding)
                paddingRight = r.getDimensionPixelSize(R.dimen.sushi_tag_large_horizontal_padding)
                paddingBottom = r.getDimensionPixelSize(R.dimen.sushi_tag_large_vertical_padding)
                textSizePx = r.getDimension(R.dimen.sushi_tag_large_textsize)
                textApprStyleRes = R.style.TextAppearance_Sushi_SemiBold
            }
            TagSize.MEDIUM -> {
                paddingTop = r.getDimensionPixelSize(R.dimen.sushi_tag_medium_vertical_padding)
                paddingLeft = r.getDimensionPixelSize(R.dimen.sushi_tag_medium_horizontal_padding)
                paddingRight = r.getDimensionPixelSize(R.dimen.sushi_tag_medium_horizontal_padding)
                paddingBottom = r.getDimensionPixelSize(R.dimen.sushi_tag_medium_vertical_padding)
                textSizePx = r.getDimension(R.dimen.sushi_tag_medium_textsize)
                textApprStyleRes = R.style.TextAppearance_Sushi_SemiBold
            }
            TagSize.SMALL -> {
                paddingTop = r.getDimensionPixelSize(R.dimen.sushi_tag_small_vertical_padding)
                paddingLeft = r.getDimensionPixelSize(R.dimen.sushi_tag_small_horizontal_padding)
                paddingRight = r.getDimensionPixelSize(R.dimen.sushi_tag_small_horizontal_padding)
                paddingBottom = r.getDimensionPixelSize(R.dimen.sushi_tag_small_vertical_padding)
                textSizePx = r.getDimension(R.dimen.sushi_tag_small_textsize)
            }
            TagSize.TINY -> {
                paddingTop = r.getDimensionPixelSize(R.dimen.sushi_tag_tiny_vertical_padding)
                paddingLeft = r.getDimensionPixelSize(R.dimen.sushi_tag_tiny_horizontal_padding)
                paddingRight = r.getDimensionPixelSize(R.dimen.sushi_tag_tiny_horizontal_padding)
                paddingBottom = r.getDimensionPixelSize(R.dimen.sushi_tag_tiny_vertical_padding)
                textSizePx = r.getDimension(R.dimen.sushi_tag_tiny_textsize)
            }
            TagSize.NANO -> {
                paddingTop = r.getDimensionPixelSize(R.dimen.sushi_tag_tiny_vertical_padding)
                paddingLeft = r.getDimensionPixelSize(R.dimen.sushi_tag_tiny_horizontal_padding)
                paddingRight = r.getDimensionPixelSize(R.dimen.sushi_tag_tiny_horizontal_padding)
                paddingBottom = r.getDimensionPixelSize(R.dimen.sushi_tag_tiny_vertical_padding)
                textSizePx = r.getDimension(R.dimen.sushi_tag_nano_textsize)
                textApprStyleRes = R.style.TextAppearance_Sushi_SemiBold
            }
        }
        if (tagType == TagType.CAPSULE || tagType == TagType.CAPSULE_OUTLINE) {
            // Capsule tags look better with a little more padding
            paddingLeft = (paddingLeft * 1.5).toInt()
            paddingRight = (paddingRight * 1.5).toInt()
        }
        if (mPadding != -1) {
            paddingLeft = mPadding
            paddingRight = mPadding
            paddingBottom = mPadding
            paddingTop = mPadding
        }
        if (mPaddingLeft != -1) paddingLeft = mPaddingLeft
        if (mPaddingRight != -1) paddingRight = mPaddingRight
        if (mPaddingTop != -1) paddingTop = mPaddingTop
        if (mPaddingBottom != -1) paddingBottom = mPaddingBottom

        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePx)
        setTextAppearance(textApprStyleRes)
        includeFontPadding = false
    }

    @JvmStatic
    private fun SushiTag.applyBg(
        r: Float, colorList: ColorStateList,
        outlined: Boolean = false, dashed: Boolean = false,
        strokeColor: Int = 0
    ) {
        var strokeWidth = context.resources.getDimensionPixelOffset(R.dimen.sushi_spacing_pico)

        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.setColor(colorList)
        shape.cornerRadii = floatArrayOf(r, r, r, r, r, r, r, r, r)
        if (outlined) {
            setTextColor(colorList)
            if (strokeColor != 0) {
                shape.setStroke(strokeWidth, strokeColor, 10F.takeIf { dashed } ?: 0F, 10F.takeIf { dashed } ?: 0F)
            } else {
                shape.setStroke(strokeWidth, colorList, 10F.takeIf { dashed } ?: 0F, 10F.takeIf { dashed } ?: 0F)
            }
        } else {
            setTextColor(ContextCompat.getColor(context, R.color.sushi_white))
            if (strokeColor == 0) {
                strokeWidth = 0
            }
            shape.setStroke(strokeWidth, strokeColor)
        }
        setBackground(shape)
    }

    @JvmStatic
    fun SushiTag.applyBackground() {
        val roundedDrawableRadius =
            if (tagSize == TagSize.NANO) resources.getDimensionPixelOffset(R.dimen.sushi_tag_extra_rounded_corner_radius).toFloat() else resources.getDimensionPixelOffset(
                R.dimen.sushi_tag_rounded_corner_radius
            ).toFloat()
        when (tagType) {
            TagType.ROUNDED -> applyBg(
                roundedDrawableRadius, ColorStateList.valueOf(tagColor), strokeColor = borderColor
            )
            TagType.CAPSULE -> applyBg(
                resources.getDimensionPixelOffset(R.dimen.sushi_tag_capsule_corner_radius).toFloat(),
                ColorStateList.valueOf(tagColor)
            )
            TagType.ROUNDED_OUTLINE -> applyBg(
                roundedDrawableRadius, ColorStateList.valueOf(tagColor), true, strokeColor = borderColor
            )
            TagType.CAPSULE_OUTLINE -> applyBg(
                resources.getDimensionPixelOffset(R.dimen.sushi_tag_capsule_corner_radius).toFloat(),
                ColorStateList.valueOf(tagColor),
                true,
                strokeColor = borderColor
            )
            TagType.ROUNDED_DASHED -> applyBg(
                roundedDrawableRadius,
                ColorStateList.valueOf(tagColor),
                true,
                true,
                strokeColor = borderColor
            )
            TagType.CAPSULE_DASHED -> applyBg(
                resources.getDimensionPixelOffset(R.dimen.sushi_tag_capsule_corner_radius).toFloat(),
                ColorStateList.valueOf(tagColor),
                true,
                true,
                strokeColor = borderColor
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
            TagSize.NANO -> R.dimen.sushi_rating_tag_nano_minwidth
            else -> R.dimen.sushi_rating_tag_large_minwidth
        }
    }
}