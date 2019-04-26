package com.zomato.sushilib.molecules.ratings

import android.content.Context
import android.content.res.ColorStateList
import android.support.annotation.IntRange
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import com.zomato.sushilib.R
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

    @IntRange(from = 0, to = 5)
    var rating: Int = 0



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
                }
            )
        }

        ratingTags.forEach { tag ->
            addView(tag)
        }
    }

}