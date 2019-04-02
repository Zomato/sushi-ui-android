package com.zomato.sushilib.molecule.rating

import android.support.annotation.ColorInt

interface SushiRatingViewSetter {
    fun setBaseColor(@ColorInt color: Int)
    fun shouldShowText(showText: Boolean)
    fun setRatingNumber(rating : Int)
}