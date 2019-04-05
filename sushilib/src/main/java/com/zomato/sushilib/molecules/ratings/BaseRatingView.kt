package com.zomato.sushilib.molecules.ratings

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.zomato.sushilib.R

abstract class BaseRatingView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private val itemOnClickListener = View.OnClickListener {
        val rating = it.getTag(R.id.rating_index) as? Int
        rating ?: return@OnClickListener
        setRating(rating)
    }

    init {
        orientation = HORIZONTAL
        var rating = 5
        context.theme?.obtainStyledAttributes(attributeSet, R.styleable.BaseRating, defStyleAttr, defStyleRes)?.apply {
            rating = getInt(R.styleable.BaseRating_rating, 5)
            recycle()
        }

        for (i in 1..5) {
            val v = createRatingChild(i)
            v.setTag(R.id.rating_index, i)
            addView(v)
            if (isRatingClickable()) v.setOnClickListener(itemOnClickListener)
            (v as? SushiRatingViewSetter)?.setRatingNumber(i)
        }
        setRating(rating)
    }

    fun setRating(rating: Int) {
        val ratingColor = RatingHelper.getRatingColor(context, rating)
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

    abstract fun createRatingChild(rating: Int): View
    abstract fun isRatingClickable() : Boolean

}