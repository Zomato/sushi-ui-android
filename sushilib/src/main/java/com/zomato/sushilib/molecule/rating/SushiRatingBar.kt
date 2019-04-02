package com.zomato.sushilib.molecule.rating

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.zomato.sushilib.R

class SushiRatingBar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private val ratingColors = IntArray(5) {
        when (it) {
            0 -> R.color.sushi_red_500
            1 -> R.color.sushi_red_300
            2 -> R.color.sushi_yellow_500
            3 -> R.color.sushi_green_300
            4 -> R.color.sushi_green_500
            else -> R.color.sushi_red_500
        }
    }

    private val itemOnClickListener = View.OnClickListener {
        val rating = it.getTag(R.id.rating_index) as? Int
        rating ?: return@OnClickListener
        val ratingColor = resources.getColor(ratingColors[rating - 1])
        for (i in 0..(rating - 1)) {
            (getChildAt(i) as? SushiBigRatingTag)?.run {
                setBaseColor(ratingColor)
                isSelected = true
                shouldShowText(i == rating - 1)
            }
        }

        for (i in (rating)..(childCount - 1)) {
            (getChildAt(i) as? SushiBigRatingTag)?.run {
                isSelected = false
                shouldShowText(true)
            }
        }
    }

    init {
        orientation = HORIZONTAL
        for (i in 1..5) {
            addView(SushiBigRatingTag(context).apply {
                val params = generateDefaultLayoutParams()
                (params as? MarginLayoutParams)?.let {
                    if (i != 5) {
                        it.marginEnd = resources.getDimensionPixelSize(R.dimen.sushi_spacing_between)
                    }
                }
                layoutParams = params
                setRatingNumber(i)
                setTag(R.id.rating_index, i)
                setBaseColor(resources.getColor(ratingColors[i - 1]))
                setOnClickListener(itemOnClickListener)
            })
        }

    }

}