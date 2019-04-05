package com.zomato.sushilib.molecules.ratings

import android.content.Context
import android.support.annotation.ColorInt
import com.zomato.sushilib.R
import java.lang.RuntimeException

object RatingHelper {
    private val ratingColors = IntArray(5) {
        when (it) {
            0 -> R.color.sushi_orange_500
            1 -> R.color.sushi_yellow_500
            2 -> R.color.sushi_green_500
            3 -> R.color.sushi_green_600
            4 -> R.color.sushi_green_700
            else -> R.color.sushi_red_500
        }
    }

    @ColorInt
    fun getRatingColor(context: Context,rating : Int) : Int {
        if(rating<1 || rating>5) throw RuntimeException("Invalid rating exception")
        return context.resources.getColor(ratingColors[rating-1])
    }
}