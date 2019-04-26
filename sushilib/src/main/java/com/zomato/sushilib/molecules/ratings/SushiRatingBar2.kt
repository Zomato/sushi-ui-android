package com.zomato.sushilib.molecules.ratings

import android.content.Context
import android.content.res.ColorStateList
import android.support.annotation.ColorInt
import android.support.annotation.IntRange
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.TagType
import com.zomato.sushilib.atoms.textviews.SushiTag

/**
 * created by championswimmer on 26/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
class SushiRatingBar2 @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0, defStyleRes: Int = 0
) : LinearLayout(ctx, attrs, defStyleAttr, defStyleRes) {

    private var ratingTags: ArrayList<SushiTag> = ArrayList(5)

    var ratingColorStateList = resources.getColorStateList(R.color.sushi_rating_color_selector)

    @get:IntRange(from = 0, to = 5)
    @setparam:IntRange(from = 0, to = 5)
    var rating: Int = 0
        set(value) {
            field = value
            reapplyRatingToAllTags()
        }

    @ColorInt
    private fun getRatingColor(r: Int): Int {
        return ratingColorStateList.getColorForState(
            when (r) {
                1 -> intArrayOf(R.attr.state_rating_1)
                2 -> intArrayOf(R.attr.state_rating_2)
                3 -> intArrayOf(R.attr.state_rating_3)
                4 -> intArrayOf(R.attr.state_rating_4)
                5 -> intArrayOf(R.attr.state_rating_5)
                else -> intArrayOf()
            },
            0
        )
    }

    init {
        val tagMargin = resources.getDimensionPixelSize(R.dimen.sushi_spacing_nano)
        val tagWidth = resources.getDimensionPixelSize(R.dimen.sushi_rating_tag_width)

        for (i in 1..5) {
            ratingTags.add(
                SushiTag(context, null, R.attr.ratingTagStyle).apply {
                    layoutParams = LayoutParams(tagWidth, WRAP_CONTENT).apply {
                        setMargins(tagMargin, tagMargin, tagMargin, tagMargin)
                    }
                    text = "$i"
                    setOnClickListener { rating = i }
                }
            )
        }

        ratingTags.forEach { tag ->
            addView(tag)
        }
        reapplyRatingToAllTags()
    }

    private fun reapplyRatingToAllTags() {
        for (i in 1..5) {
            if (i > rating) {
                ratingTags[i - 1].apply {
                    tagColor = getRatingColor(0)
                    tagType = TagType.CAPSULE_OUTLINE
                    setTextColor(ColorStateList.valueOf(getRatingColor(0)))
                }
            }
            if (i <= rating) {
                ratingTags[i - 1].apply {
                    tagColor = getRatingColor(rating)
                    tagType = TagType.CAPSULE

                    if (i < rating) {
                        setTextColor(ColorStateList.valueOf(getRatingColor(rating)))
                    }

                }
            }
        }
    }

}